package fr.joand.root;

/**
 * Created by user on 30/10/2016.
 */
public class OldNode {
    /*
    final String data;
    OldNode parent;
    List<OldNode> children = new ArrayList<>();


    //  https://stackoverflow.com/questions/2603692/what-is-the-difference-between-tree-depth-and-height
    int height = 1;

    public OldNode(String data) {
        this.data = data;
    }

    public void addChild(OldNode child) {
        this.children.add(child);
        computeHeight();

        child.setParent(this);
    }

    void computeHeight() {
        this.height = 1 + Root.getMaxHeight(this.children);
        if (this.parent != null) {
            this.parent.computeHeight();
        }
    }

    public String getData() {
        return data;
    }

    public OldNode getParent() {
        return parent;
    }

    public void setParent(OldNode parent) {
        this.parent = parent;
    }

    public List<OldNode> getChildren() {
        return children;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OldNode node = (OldNode) o;
        return Objects.equals(data, node.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
    //*/
}
