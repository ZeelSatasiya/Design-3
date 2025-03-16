public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }
            else if((nextEl = st.peek().next()).isInteger()){
                return true;
            }
            else{
                st.push(nextEl.getList().iterator());
            }
        }
        return false;
    }
}


// public class NestedIterator implements Iterator<Integer> {
//     Queue<Integer> q;
//     public NestedIterator(List<NestedInteger> nestedList) {
//         q = new LinkedList<>();
//         flattenList(nestedList);
//     }

//     @Override
//     public Integer next() {
//         return q.poll();
//     }

//     @Override
//     public boolean hasNext() {
//         return !q.isEmpty();
//     }

//     public void flattenList(List<NestedInteger> nestedList){
//         for(NestedInteger element : nestedList){
//             if(element.isInteger()){
//                 q.add(element.getInteger());
//             }
//             else{
//                 flattenList(element.getList());
//             }
//         }
//     }
// }
