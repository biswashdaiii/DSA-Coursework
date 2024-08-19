//3a

import java.util.Arrays;

public class FriendshipRestrictions {
    
    static class UnionFind {
        int[] parent;
        int[] rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
                return true;
            }
            return false;
        }
    }

    public static String[] processFriendRequests(int n, int[][] restrictions, int[][] requests) {
        UnionFind uf = new UnionFind(n);
        String[] result = new String[requests.length];

        for (int i = 0; i < requests.length; i++) {
            int house1 = requests[i][0];
            int house2 = requests[i][1];

            int root1 = uf.find(house1);
            int root2 = uf.find(house2);

            boolean canBeFriends = true;

            for (int[] restriction : restrictions) {
                int restrictedHouse1 = restriction[0];
                int restrictedHouse2 = restriction[1];
                int restrictedRoot1 = uf.find(restrictedHouse1);
                int restrictedRoot2 = uf.find(restrictedHouse2);

                if ((root1 == restrictedRoot1 && root2 == restrictedRoot2) ||
                    (root1 == restrictedRoot2 && root2 == restrictedRoot1)) {
                    canBeFriends = false;
                    break;
                }
            }

            if (canBeFriends) {
                uf.union(house1, house2);
                result[i] = "approved";
            } else {
                result[i] = "denied";
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int n1 = 3;
        int[][] restrictions1 = {{0, 1}};
        int[][] requests1 = {{0, 2}, {2, 1}};
        System.out.println(Arrays.toString(processFriendRequests(n1, restrictions1, requests1))); // [approved, denied]

        int n2 = 5;
        int[][] restrictions2 = {{0, 1}, {1, 2}, {2, 3}};
        int[][] requests2 = {{0, 4}, {1, 2}, {3, 1}, {3, 4}};
        System.out.println(Arrays.toString(processFriendRequests(n2, restrictions2, requests2))); // [approved, denied, approved, denied]
    }
}
