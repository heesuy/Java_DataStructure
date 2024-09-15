package BST;

public class BinarySearchTree implements IndexInterface<TreeNode> {
    // oop 원칙에 따라서 private
    private TreeNode root;
    public BinarySearchTree(){
        root = null;
    }



    @Override
    public TreeNode search(Comparable searchKey) {
        return searchItem(root,searchKey);
    }
// oop원칙에 따라서 private
    private TreeNode searchItem(TreeNode tNode, Comparable searchKey){

        if(tNode == null){
            return null; // 검색 실패
        }
        else if (searchKey.compareTo(tNode.key) == 0){
            return tNode;
        }
        else if (searchKey.compareTo(tNode.key) < 0){
            return searchItem(tNode.left,searchKey);
        }
        else
            return searchItem(tNode.right,searchKey);
    }


    @Override
    public void insert(Comparable x) {

    }

    @Override
    public void delete(Comparable searchKey) {
    root = deleteItem(root,searchKey);
    }

    private TreeNode deleteItem(TreeNode tNode, Comparable searchKey){
        if(tNode == null){return null;}
        else{
            if(searchKey == tNode.key)
                tNode = deleteNode(tNode);
            else if (searchKey.compareTo(tNode.key) < 0)
                tNode.left = deleteItem(tNode.left,searchKey);
            else
                tNode.right = deleteItem(tNode.right,searchKey);
            return tNode;
        }
    }

    private TreeNode deleteNode(TreeNode tNode){
        // tNode는 세가지의 유형임 1. 비출산 2. 외동 3. 나머지
        if((tNode.left == null) && (tNode.right == null)) return null;
        else if (tNode.left == null)
            return tNode.right;
        else if (tNode.right == null)
            return tNode.left;
        else{

            returnPair rPair = deleteMinItem(tNode.right);
            tNode.key = rPair.key;
            tNode.right = rPair.node;
            return tNode;
        }
    }

    // tNode.left가 null일경우 목표 키의 바로 오른쪽 값에 도달한 것
    // 아니라면 아직 도달 못한 경우라서 재귀적으로 탐색 진행
    /*- **트리 구조 유지**:
  최소 노드를 찾고 제거한 후, 트리의 구조를 조정하여 이진 탐색 트리 속성을 유지해야 합니다.
  최소 노드의 오른쪽 자식(없을 경우 `null`)을 부모의 새로운 왼쪽 자식으로 설정합니다.
  이는 왼쪽 서브트리의 모든 노드가 여전히 루트 노드보다 작고, 오른쪽 서브트리의 모든 노드가 여전히 크도록 보장합니다.
*/ //tNode.left = rPair.node; // if 분기에서의 tNode.right
    private returnPair deleteMinItem(TreeNode tNode){
        if(tNode.left == null){
            return new returnPair(tNode.key,tNode.right);
        }
        else{
            returnPair rPair = deleteMinItem(tNode.left);
            tNode.left = rPair.node;
            rPair.node = tNode;
            return rPair;
        }
    }

    private class returnPair{

        private Comparable key;
        private TreeNode node;
        public returnPair(Comparable key, TreeNode node){
            this.key = key;
            this.node = node;
        }
    }
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void clear() {
    root = null;
    }
}
