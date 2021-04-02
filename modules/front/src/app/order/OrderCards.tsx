import * as React from "react";
import { observer } from "mobx-react";

import { Order } from "../../cuba/entities/task_Order";
import { Card } from "antd";
import {
  collection,
  MainStoreInjected,
  injectMainStore
} from "@cuba-platform/react-core";
import {
  addPagingParams,
  createPagingConfig,
  defaultPagingConfig,
  EntityProperty,
  Paging,
  setPagination,
  Spinner
} from "@cuba-platform/react-ui";
import { action, IReactionDisposer, observable, reaction } from "mobx";
import { PaginationConfig } from "antd/es/pagination";
import { RouteComponentProps } from "react-router";

type Props = MainStoreInjected & RouteComponentProps;

@injectMainStore
@observer
export class OrderCards extends React.Component<Props> {
  dataCollection = collection<Order>(Order.NAME, {
    view: "order-view",
    sort: "-updateTs",
    loadImmediately: false
  });

  @observable paginationConfig: PaginationConfig = { ...defaultPagingConfig };
  reactionDisposer: IReactionDisposer;
  fields = ["date", "amount", "account"];

  componentDidMount(): void {
    // to disable paging config pass 'true' as disabled param in function below
    this.paginationConfig = createPagingConfig(this.props.location.search);

    this.reactionDisposer = reaction(
      () => this.paginationConfig,
      paginationConfig =>
        setPagination(paginationConfig, this.dataCollection, true)
    );
    setPagination(this.paginationConfig, this.dataCollection, true);
  }

  componentWillUnmount() {
    this.reactionDisposer();
  }

  render() {
    const { status, items, count } = this.dataCollection;

    if (status === "LOADING") return <Spinner />;

    return (
      <div className="narrow-layout">
        {items.map(e => (
          <Card
            title={e.id}
            key={e.id ? e.id : undefined}
            style={{ marginBottom: "12px" }}
          >
            {this.fields.map(p => (
              <EntityProperty
                entityName={Order.NAME}
                propertyName={p}
                value={e[p]}
                key={p}
              />
            ))}
          </Card>
        ))}

        {!this.paginationConfig.disabled && (
          <div style={{ margin: "12px 0 12px 0", float: "right" }}>
            <Paging
              paginationConfig={this.paginationConfig}
              onPagingChange={this.onPagingChange}
              total={count}
            />
          </div>
        )}
      </div>
    );
  }

  @action onPagingChange = (current: number, pageSize: number) => {
    this.props.history.push(addPagingParams("orderCards", current, pageSize));
    this.paginationConfig = { ...this.paginationConfig, current, pageSize };
  };
}
