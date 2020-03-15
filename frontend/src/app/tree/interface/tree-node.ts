export interface TreeNode {
    id?: number;
    parent: number;
    value: number;
    children: Array<TreeNode>;
}
