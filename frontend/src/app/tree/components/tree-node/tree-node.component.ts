import {AfterViewInit, Component, Input, OnDestroy, OnInit, QueryList, ViewChild, ViewChildren} from '@angular/core';
import {TreeNode} from "../../interface/tree-node";
import {EnvService} from "../../../core/service/env.service";
import {HttpClient} from "@angular/common/http";
import {FormControl} from "@angular/forms";
import {debounceTime} from "rxjs/operators";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-tree-node',
    templateUrl: './tree-node.component.html',
    styleUrls: ['./tree-node.component.scss']
})
export class TreeNodeComponent implements OnInit, OnDestroy, AfterViewInit {
    @ViewChildren(TreeNodeComponent) components: QueryList<TreeNodeComponent>;
    @Input() node: TreeNode;
    expanded = true;

    valueInput = new FormControl();
    private subscription: Subscription;
    private leafValue: number;

    @Input() update() {
        this.updateNode();
    }

    constructor(
        private envService: EnvService,
        private httpClient: HttpClient
    ) {
    }

    ngOnInit(): void {
        this.prepareChildren();
        this.updateNode()
    }

    updateNode() {
        if (!(this.node.id)) {
            this.getId();
        }

        if (this.components) {
            this.components.forEach(value => {
                value.update();
            });
        }

        if (this.node.children.length == 0 && !!this.node.parentNode) {
            this.countLeafValue();
        } else {
            this.leafValue = null;
        }
    }

  createChild() {
      this.expanded = true;
      this.node.children.push(
          {
              parent: this.node.id,
              parentNode: this.node,
              children: new Array<TreeNode>(),
              value: null
          }
      );
      this.updateNode();
  }

    delete() {
        this.httpClient.delete(this.envService.getApiUrl(`/node/${this.node.id}`)).subscribe(
            value => {
                this.node.parentNode.children.forEach(
                    (node, index) => {
                        if (this.node === node) {
                            this.node.parentNode.children.splice(index, 1);
                        }
                    }
                );
                console.log("deleted");
            }
        );
    }

    expand() {
        this.expanded = !this.expanded;
    }

    ngAfterViewInit(): void {
        this.subscription = this.valueInput.valueChanges
            .pipe(
                debounceTime(500)
            )
            .subscribe(
                value => {
                    this.updateValue(value);
                }
            );
    }

    private prepareChildren() {
        this.node.children.forEach(
            value => {
                value.parentNode = this.node
            }
        );
    }

    ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }

    private getId() {
        this.httpClient.post(this.envService.getApiUrl("/node"), {"parentId": this.node.parent}).subscribe(
            value => {
                this.node.id = <number>value;
            }
        );
    }

    private updateValue(value: any) {
        this.httpClient.put(this.envService.getApiUrl(`/node/${this.node.id}`), {"value": value}).subscribe(
            value => {
                this.updateNode();
                console.log("updated");
            }
        );
    }

    private countLeafValue() {
        console.log("counting leaf value");
        this.leafValue = this.countParent(this.node.parentNode);
    }

    private countParent(node: TreeNode) {
        return node.value + (!!node.parentNode ? this.countParent(node.parentNode) : 0);
    }
}
