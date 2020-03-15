export interface TreeNode {
    id?: number;
    parent: number;
    parentNode?: TreeNode;
    value: number;
    children: Array<TreeNode>;
}
