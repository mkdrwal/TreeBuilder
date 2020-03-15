import {Component, OnInit} from '@angular/core';
import {Tree} from "../../interface/tree";
import {EnvService} from "../../../core/service/env.service";
import {HttpClient} from "@angular/common/http";

@Component({
    selector: 'app-tree-builder',
    templateUrl: './tree-builder.component.html',
    styleUrls: ['./tree-builder.component.scss']
})
export class TreeBuilderComponent implements OnInit {
    public tree: Tree;

    constructor(
        private envService: EnvService,
        private httpClient: HttpClient
    ) {
    }

    ngOnInit(): void {
        this.load();
    }

    private load() {
        this.httpClient.get(this.envService.getApiUrl("/tree")).subscribe(
            value => {
                this.tree = <Tree>value;
                console.log(this.tree);
            }
        )
    }
}
