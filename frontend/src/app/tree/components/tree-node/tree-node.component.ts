import {AfterViewInit, Component, Input, OnDestroy, OnInit} from '@angular/core';
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
    @Input() node: TreeNode;
    @Input() parent: TreeNode;

    valueInput = new FormControl();
    private subscription: Subscription;

    constructor(
        private envService: EnvService,
        private httpClient: HttpClient
    ) {
    }

    ngOnInit(): void {
        if (!(this.node.id)) {
            this.getId();
    }
  }

  createChild() {
    this.node.children.push(
        {
            parent: this.node.id,
            children: new Array<TreeNode>(),
            value: null
        }
    );
  }

    private getId() {
        this.httpClient.post(this.envService.getApiUrl("/node"), {"parentId": this.parent.id}).subscribe(
            value => {
                this.node.id = <number>value;
            }
        );
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

    ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }

    private updateValue(value: any) {
        this.httpClient.put(this.envService.getApiUrl(`/node/${this.node.id}`), {"value": value}).subscribe(
            value => {
                console.log("updated");
            }
        );
    }
}
