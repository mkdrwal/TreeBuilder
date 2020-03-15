import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TreeBuilderComponent} from './components/tree-builder/tree-builder.component';
import {TreeNodeComponent} from './components/tree-node/tree-node.component';


@NgModule({
    declarations: [TreeBuilderComponent, TreeNodeComponent],
    exports: [
        TreeBuilderComponent
    ],
    imports: [
        CommonModule
    ]
})
export class TreeModule {
}
