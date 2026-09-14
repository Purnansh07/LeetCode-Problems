public class NestedIterator implements Iterator<Integer> {

    private final Stack<NestedInteger> stack = new Stack<>();

    public NestedIterator(List<NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        hasNext();
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            NestedInteger top = stack.peek();

            if (top.isInteger()) {
                return true;
            }

            // Remove the nested list
            stack.pop();

            List<NestedInteger> list = top.getList();

            // Push in reverse order to preserve original order
            for (int i = list.size() - 1; i >= 0; i--) {
                stack.push(list.get(i));
            }
        }

        return false;
    }
}