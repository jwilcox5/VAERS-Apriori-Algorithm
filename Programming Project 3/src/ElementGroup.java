public class ElementGroup
{
    private String elements;
    private int elementCount;

    public ElementGroup(String elements)
    {
        this.elements = elements;
        this.elementCount = 0;
    }

    public String getElements()
    {
        return this.elements;
    }

    public void incElemCount()
    {
        this.elementCount++;
    }

    public int getElemCount()
    {
        return this.elementCount;
    }
}