public class Solution {
    
    private void solve(List<List<Integer>> res, int currentIndex, int count, List<Integer> tmp,
                       int[] candidates, int target){
        if (count >= target) {
            if(count == target)
                res.add(new LinkedList<>(tmp));
            return;
        }
    

        for (int i = currentIndex; i < candidates.length; i++){

            if (count + candidates[i] > target) {
                break;
            }
            tmp.add(candidates[i]);
            solve(res, i + 1, count + candidates[i], tmp, candidates, target);
            tmp.remove(tmp.size() - 1);
        }
    }
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        List<Integer> tmp = new LinkedList<>();
        Arrays.sort(candidates);
        solve(res, 0, 0, tmp, candidates, target);
        
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        for (List<Integer> numList : res) {
            set.add(numList);
        }
        res = new LinkedList<List<Integer>>();
        for (List<Integer> numList : set) {
            res.add(numList);
        }
        return res;
    }
}