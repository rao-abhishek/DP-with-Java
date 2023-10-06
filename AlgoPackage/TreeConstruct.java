package AlgoPackage;

interface ITreeConstruct {
    TreeNode constructBSTTree(int[] preOrder);

    TreeNode constructTree(int[] preOrder, int[] inorder);

    void displayInOrderTree(TreeNode root);

    void displayPreOrderTree(TreeNode root);

    void displayPostOrderTree(TreeNode root);
}

public class TreeConstruct implements ITreeConstruct {

    public static void run() {
        System.out.println("\n\nRunning Tree Algorithm");
        TreeConstruct tc = new TreeConstruct();
        int[] arr1 = new int[] { 3, 9, 20, 15, 7 };
        int[] arr2 = new int[] { 9, 3, 15, 20, 7 };
        TreeNode root1 = tc.constructBSTTree(arr1);
        TreeNode root2 = tc.constructTree(arr1, arr2);
        tc.displayPreOrderTree(root2);
        System.out.print("\n");
        tc.displayInOrderTree(root2);
        System.out.print("\n");
        tc.displayPostOrderTree(root2);
        System.out.print("\n");
        tc.displayPreOrderTree(root1);
    }

    public void displayPreOrderTree(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.val + " -> ");
        displayPreOrderTree(root.left);
        displayPreOrderTree(root.right);
    }

    public void displayInOrderTree(TreeNode root) {
        if (root == null) {
            return;
        }

        displayInOrderTree(root.left);
        System.out.print(root.val + " -> ");
        displayInOrderTree(root.right);
    }

    public void displayPostOrderTree(TreeNode root) {
        if (root == null) {
            return;
        }

        displayPostOrderTree(root.left);
        displayPostOrderTree(root.right);
        System.out.print(root.val + " -> ");
    }

    public TreeNode constructTree(int[] preOrder, int[] inOrder) {
        return constructTree(preOrder, inOrder, 0, 0, inOrder.length);
    }

    public TreeNode constructTree(int[] preorder, int[] inorder, int p, int startInd, int endInd) {
        if (p >= preorder.length || startInd >= endInd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[p]);
        int rootInd = -1;
        for (int i = startInd; i < endInd; i++) {
            if (preorder[p] == inorder[i]) {
                rootInd = i;
            }
        }

        if (rootInd == -1) {
            return null;
        }

        int leftSubtreeSize = rootInd - startInd;
        root.left = constructTree(preorder, inorder, p + 1, startInd, rootInd);
        root.right = constructTree(preorder, inorder, p + leftSubtreeSize + 1, rootInd + 1, endInd);

        return root;
    }

    public TreeNode constructBSTTree(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            addNode(root, preorder[i]);
        }

        return root;
    }

    private void addNode(TreeNode root, int val) {
        TreeNode node = root;
        TreeNode prevNode = node;
        while (node != null) {
            prevNode = node;
            if (val < node.val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        node = prevNode;
        if (val < node.val) {
            node.left = new TreeNode(val);
        } else {
            node.right = new TreeNode(val);
        }

    }

}
