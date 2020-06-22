class Main {

    public static void main(final String[] args) {
        // Create a new Solution instance
        final Solution solution = new Solution();
        // Create a test case
        final int[] test_values = {1,2,3,4,5};
        final ListNode test_lListNode=solution.assignListNode(test_values);
        final int test_K = 2;
        // Get the answer
        final ListNode answer = solution.reverseKGroup(test_lListNode, test_K);
        // Print the answer
        System.out.println(answer);
    }


}