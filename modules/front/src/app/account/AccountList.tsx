import * as React from "react";
import { observer } from "mobx-react";
import { Link } from "react-router-dom";
import { observable } from "mobx";
import { Modal, Button } from "antd";

import {
  collection,
  injectMainStore,
  MainStoreInjected
} from "@cuba-platform/react-core";
import { DataTable, Spinner, ImagePreview } from "@cuba-platform/react-ui";

import { Account } from "../../cuba/entities/task_Account";
import { SerializedEntity } from "@cuba-platform/rest";
import { AccountManagement } from "./AccountManagement";
import {
  FormattedMessage,
  injectIntl,
  WrappedComponentProps
} from "react-intl";

@injectMainStore
@observer
class AccountListComponent extends React.Component<
  MainStoreInjected & WrappedComponentProps
> {
  dataCollection = collection<Account>(Account.NAME, {
    view: "account-view",
    sort: "-updateTs"
  });
  @observable selectedRowKey: string | undefined;

  fields = ["photo", "name", "lastName", "middleName", "contacts"];

  columns = [
    {
      title: 'Avatar',
      dataIndex: 'photo',
      render: (text: any, record: any) => {
        return (
          <div>
            <img src={record.photo} alt={record.photo.name}/>
          </div>
        );
      },
    },
    {
      title: 'Name',
      dataIndex: 'name',
      render: (text: any, record: {
        middleName: any;
        lastName: any;
        name: any;
        photo: string | undefined;  }): any => {
        return (
          <div>
            <p>
              {record.name}
              {' '}
              {record.lastName}
              {' '}
              {record.middleName}
            </p>
          </div>
        );
      },
    },
  ];

  showDeletionDialog = (e: SerializedEntity<Account>) => {
    Modal.confirm({
      title: this.props.intl.formatMessage(
        { id: "management.browser.delete.areYouSure" },
        { instanceName: e._instanceName }
      ),
      okText: this.props.intl.formatMessage({
        id: "management.browser.delete.ok"
      }),
      cancelText: this.props.intl.formatMessage({ id: "common.cancel" }),
      onOk: () => {
        this.selectedRowKey = undefined;
        return this.dataCollection.delete(e);
      }
    });
  };

  render() {
    if (this.props.mainStore?.isEntityDataLoaded() !== true) return <Spinner />;

    const buttons = [
      <Link
        to={AccountManagement.PATH + "/" + AccountManagement.NEW_SUBPATH}
        key="create"
      >
        <Button
          htmlType="button"
          style={{ margin: "0 12px 12px 0" }}
          type="primary"
          icon="plus"
        >
          <span>
            <FormattedMessage id="common.create" />
          </span>
        </Button>
      </Link>,
      <Link to={AccountManagement.PATH + "/" + this.selectedRowKey} key="edit">
        <Button
          htmlType="button"
          style={{ margin: "0 12px 12px 0" }}
          disabled={!this.selectedRowKey}
          type="default"
        >
          <FormattedMessage id="common.edit" />
        </Button>
      </Link>,
      <Button
        htmlType="button"
        style={{ margin: "0 12px 12px 0" }}
        disabled={!this.selectedRowKey}
        onClick={this.deleteSelectedRow}
        key="remove"
        type="default"
      >
        <FormattedMessage id="common.remove" />
      </Button>
    ];

    return (
      <DataTable
        dataCollection={this.dataCollection}
        fields={this.fields}
        onRowSelectionChange={this.handleRowSelectionChange}
        hideSelectionColumn={true}
        buttons={buttons}
/*        tableProps={{
          columns: this.columns,
        }}*/

      />
    );
  }

  getRecordById(id: string): SerializedEntity<Account> {
    const record:
      | SerializedEntity<Account>
      | undefined = this.dataCollection.items.find(record => record.id === id);

    if (!record) {
      throw new Error("Cannot find entity with id " + id);
    }

    return record;
  }

  handleRowSelectionChange = (selectedRowKeys: string[]) => {
    this.selectedRowKey = selectedRowKeys[0];
  };

  deleteSelectedRow = () => {
    this.showDeletionDialog(this.getRecordById(this.selectedRowKey!));
  };
}

const AccountList = injectIntl(AccountListComponent);

export default AccountList;
