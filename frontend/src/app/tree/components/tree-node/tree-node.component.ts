import {Component, Input, OnInit} from '@angular/core';
import {TreeNode} from "../../interface/tree-node";
import {EnvService} from "../../../core/service/env.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-tree-node',
  templateUrl: './tree-node.component.html',
  styleUrls: ['./tree-node.component.scss']
})
export class TreeNodeComponent implements OnInit {
  @Input() node: TreeNode;
  @Input() parent: TreeNode;

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

  setValue($event: any) {
    console.log($event.target.value)
  }

  createChild() {
    this.node.children.push(
        {
          parent: this.parent.id,
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
}
