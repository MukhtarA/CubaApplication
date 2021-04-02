import * as React from "react";
import {FormEvent} from "react";
import {Alert, Button, Card, Form, message, Table} from "antd";
import {observer} from "mobx-react";
import {OrderManagement} from "./OrderManagement";
import {FormComponentProps} from "antd/lib/form";
import {Link, Redirect} from "react-router-dom";
import {IReactionDisposer, observable, reaction, toJS} from "mobx";
import {
  FormattedMessage,
  injectIntl,
  WrappedComponentProps
} from "react-intl";

import {
  instance,
  MainStoreInjected,
  injectMainStore, collection,
} from "@cuba-platform/react-core";

import {
  Field,
  withLocalizedForm,
  extractServerValidationErrors,
  constructFieldsWithErrors,
  clearFieldErrors,
  MultilineText,
  Spinner,
  Msg,
  FormField,
} from "@cuba-platform/react-ui";

import "../../app/App.css";

import {Order} from "../../cuba/entities/task_Order";
import {Account} from "../../cuba/entities/task_Account";
import Column from "antd/es/table/Column";
import {Product} from "../../cuba/entities/task_Product";

type Props = FormComponentProps & EditorProps & MainStoreInjected;

type EditorProps = {
  entityId: string;
};

@injectMainStore
@observer
class OrderEditComponent extends React.Component<Props & WrappedComponentProps> {
  dataInstance = instance<Order>(Order.NAME, {
    view: "order-view",
    loadImmediately: false
  });
  accountDc = collection<Account>(Account.NAME, {view: '_minimal', sort: 'name'});

  @observable updated = false;
  @observable formRef: React.RefObject<Form> = React.createRef();
  reactionDisposers: IReactionDisposer[] = [];

  fields = ["date", "amount", "account"];

  @observable globalErrors: string[] = [];

  handleSubmit = (e: FormEvent) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (err) {
        message.error(
          this.props.intl.formatMessage({
            id: "management.editor.validationError"
          })
        );
        return;
      }
      this.dataInstance
        .update(
          this.props.form.getFieldsValue(this.fields),
          this.isNewEntity() ? "create" : "edit"
        )
        .then(() => {
          message.success(
            this.props.intl.formatMessage({id: "management.editor.success"})
          );
          this.updated = true;
        })
        .catch((e: any) => {
          if (e.response && typeof e.response.json === "function") {
            e.response.json().then((response: any) => {
              clearFieldErrors(this.props.form);
              const {
                globalErrors,
                fieldErrors
              } = extractServerValidationErrors(response);
              this.globalErrors = globalErrors;
              if (fieldErrors.size > 0) {
                this.props.form.setFields(
                  constructFieldsWithErrors(fieldErrors, this.props.form)
                );
              }

              if (fieldErrors.size > 0 || globalErrors.length > 0) {
                message.error(
                  this.props.intl.formatMessage({
                    id: "management.editor.validationError"
                  })
                );
              } else {
                message.error(
                  this.props.intl.formatMessage({
                    id: "management.editor.error"
                  })
                );
              }
            });
          } else {
            message.error(
              this.props.intl.formatMessage({id: "management.editor.error"})
            );
          }
        });
    });
  };

  isNewEntity = () => {
    return this.props.entityId === OrderManagement.NEW_SUBPATH;
  };

  render() {

    if (this.updated) {
      return <Redirect to={OrderManagement.PATH}/>;
    }

    const {getFieldDecorator} = this.props.form;
    const {status, lastError, load, item} = this.dataInstance;
    const {mainStore, entityId} = this.props;
    if (mainStore == null || !mainStore.isEntityDataLoaded()) {
      return <Spinner/>;
    }

    // do not stop on "COMMIT_ERROR" - it could be bean validation, so we should show fields with errors
    if (status === "ERROR" && lastError === "LOAD_ERROR") {
      return (
        <>
          <FormattedMessage id="common.requestFailed"/>.
          <br/>
          <br/>
          <Button htmlType="button" onClick={() => load(entityId)}>
            <FormattedMessage id="common.retry"/>
          </Button>
        </>
      );
    }

    return (
      <Card className="narrow-layout">
        <Form onSubmit={this.handleSubmit} layout="vertical" ref={this.formRef}>
          <Field
            entityName={Order.NAME}
            propertyName="date"
            form={this.props.form}
            formItemOpts={{style: {marginBottom: "12px"}}}
            getFieldDecoratorOpts={{}}
          />

          <Field
            entityName={Order.NAME}
            propertyName="amount"
            form={this.props.form}
            formItemOpts={{style: {marginBottom: "12px"}}}
            getFieldDecoratorOpts={{}}
          />

          <Form.Item label={<Msg entityName={Order.NAME} propertyName='account'/>}
                     key='account'
          >
            {
              getFieldDecorator('account')(
                <FormField entityName={Order.NAME}
                           propertyName='account'
                           optionsContainer={this.accountDc}
                />
              )
            }
          </Form.Item>
          {/*"name" | "price" | "quantity" */}
          <Form.Item label={<Msg entityName={Order.NAME} propertyName='product' />}
                     key='product'
          >

            <Table dataSource={item && item.product ? item!.product : []}
                   pagination={false}
                   size='middle'
                   bordered
            >
              <Column title={<Msg entityName={Product.NAME} propertyName='name' />}
                      dataIndex='name'
                      key='name'
                      sorter={(a: Product, b: Product) =>
                        a.name!.localeCompare(b.name!)
                      }
              />
              <Column title={<Msg entityName={Product.NAME} propertyName='price' />}
                      dataIndex='price'
                      key='price'
              />
              <Column title={<Msg entityName={Product.NAME} propertyName='quantity' />}
                      dataIndex='quantity'
                      key='quantity'
              />
            </Table>
          </Form.Item>

          {this.globalErrors.length > 0 && (
            <Alert
              message={<MultilineText lines={toJS(this.globalErrors)}/>}
              type="error"
              style={{marginBottom: "24px"}}
            />
          )}

          <Form.Item style={{textAlign: "center"}}>
            <Link to={OrderManagement.PATH}>
              <Button htmlType="button">
                <FormattedMessage id="common.cancel"/>
              </Button>
            </Link>
            <Button
              type="primary"
              htmlType="submit"
              disabled={status !== "DONE"}
              loading={status === "LOADING"}
              style={{marginLeft: "8px"}}
            >
              <FormattedMessage id="common.submit"/>
            </Button>
          </Form.Item>

        </Form>
      </Card>
    );
  }

  componentDidMount() {
    if (this.isNewEntity()) {
      this.dataInstance.setItem(new Order());
    } else {
      this.dataInstance.load(this.props.entityId);
    }

    this.reactionDisposers.push(
      reaction(
        () => this.dataInstance.status,
        () => {
          const {intl} = this.props;
          if (this.dataInstance.lastError != null) {
            message.error(intl.formatMessage({id: "common.requestFailed"}));
          }
        }
      )
    );

    this.reactionDisposers.push(
      reaction(
        () => this.formRef.current,
        (formRefCurrent, formRefReaction) => {
          if (formRefCurrent != null) {
            // The Form has been successfully created.
            // It is now safe to set values on Form fields.
            this.reactionDisposers.push(
              reaction(
                () => this.dataInstance.item,
                () => {
                  this.props.form.setFieldsValue(
                    this.dataInstance.getFieldValues(this.fields)
                  );
                },
                {fireImmediately: true}
              )
            );
            formRefReaction.dispose();
          }
        },
        {fireImmediately: true}
      )
    );
  }

  componentWillUnmount() {
    this.reactionDisposers.forEach(dispose => dispose());
  }
}

export default injectIntl(
  withLocalizedForm<EditorProps>({
    onValuesChange: (props: any, changedValues: any) => {
      // Reset server-side errors when field is edited
      Object.keys(changedValues).forEach((fieldName: string) => {
        props.form.setFields({
          [fieldName]: {
            value: changedValues[fieldName]
          }
        });
      });
    }
  })(OrderEditComponent)
);
