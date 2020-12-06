学习笔记

字典树

    class Trie {
        private boolean isEnd;
        private Trie[] next;
        /** Initialize your data structure here. */
        public Trie() {
            isEnd = false;
            next = new Trie[26];
        }
        
        /** Inserts a word into the trie. */
        public void insert(String word) {
            if (word == null || word.length() == 0) return;
            Trie curr = this;
            char[] words = word.toCharArray();
            for (int i = 0;i < words.length;i++) {
                int n = words[i] - 'a';
                if (curr.next[n] == null) curr.next[n] = new Trie();
                curr = curr.next[n];
            }
            curr.isEnd = true;
        }
        
        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;
        }
        
        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Trie node = searchPrefix(prefix);
            return node != null;
        }
    
        private Trie searchPrefix(String word) {
            Trie node = this;
            char[] words = word.toCharArray();
            for (int i = 0;i < words.length;i++) {
                node = node.next[words[i] - 'a'];
                if (node == null) return null;
            }
            return node;
        }
    }

并查集模板

    class UnionFind { 
        private int count = 0; 
        private int[] parent; 
        public UnionFind(int n) { 
            count = n; 
            parent = new int[n]; 
            for (int i = 0; i < n; i++) { 
                parent[i] = i;
            }
        } 
        public int find(int p) { 
            while (p != parent[p]) { 
                parent[p] = parent[parent[p]]; 
                p = parent[p]; 
            }
            return p; 
        }
        public void union(int p, int q) { 
            int rootP = find(p); 
            int rootQ = find(q); 
            if (rootP == rootQ) return; 
            parent[rootP] = rootQ; 
            count--;
        }
    }
    
双向BFS模板--例子单词接龙

        public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
            // 第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
            Set<String> wordSet = new HashSet<>(wordList);
            if (wordSet.size() == 0 || !wordSet.contains(endWord)) return 0;
            // 第 2 步：已经访问过的 word 添加到 visited 哈希表里
            Set<String> visited = new HashSet<>();
            // 分别用左边和右边扩散的哈希表代替单向 BFS 里的队列，它们在双向 BFS 的过程中交替使用
            Set<String> start = new HashSet<>();
            start.add(beginWord);
            Set<String> end = new HashSet<>();
            end.add(endWord);
            // 第 3 步：执行双向 BFS，左右交替扩散的步数之和为所求
            int step = 1;
            while (!start.isEmpty() && !end.isEmpty()) {
                // 优先选择小的哈希表进行扩散，考虑到的情况更少
                if (start.size() > end.size()) {
                    Set<String> temp = start;
                    start = end;
                    end = temp;
                }
                // 逻辑到这里，保证 beginVisited 是相对较小的集合，nextLevelVisited 在扩散完成以后，会成为新的 beginVisited
                Set<String> nextLevelVisited = new HashSet<>();
                for (String word : start) {
                    // 如果 currentWord 能够修改 1 个字符与 endWord 相同，则返回 step + 1
                    if (changeWordEveryOneLetter2(word, end, visited, wordSet, nextLevelVisited)) return step + 1;
                }
                // 原来的 beginVisited 废弃，从 nextLevelVisited 开始新的双向 BFS
                start = nextLevelVisited;
                step++;
            }
            return 0;
        }
    
        //尝试对word修改每一个字符，看看是不是能落在end中，扩展得到的新的word添加到nextLevelVisited里
        private boolean changeWordEveryOneLetter2(String word, Set<String> end, Set<String> visited, Set<String> wordSet, Set<String> nextLevelVisited) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < word.length(); i++) {
                char originChar  = chars[i];
                for (char j = 'a'; j < 'z'; j++) {
                    if (originChar == j) continue;
                    chars[i] = j;
                    String nextWord = String.valueOf(chars);
                    if (wordSet.contains(nextWord)) {
                        if (end.contains(nextWord)) return true;
                        if (!visited.contains(nextWord)) {
                            nextLevelVisited.add(nextWord);
                            visited.add(nextWord);
                        }
                    }
                }
                // 恢复，下次再用
                chars[i] = originChar;
            }
            return false;
        }
    
    
   
启发式搜索模板

    public class AStar
    	{
    		public final static int BAR = 1; // 障碍值
    		public final static int PATH = 2; // 路径
    		public final static int DIRECT_VALUE = 10; // 横竖移动代价
    		public final static int OBLIQUE_VALUE = 14; // 斜移动代价
    		
    		Queue<Node> openList = new PriorityQueue<Node>(); // 优先队列(升序)
    		List<Node> closeList = new ArrayList<Node>();
    		
    		/**
    		 * 开始算法
    		 */
    		public void start(MapInfo mapInfo)
    		{
    			if(mapInfo==null) return;
    			// clean
    			openList.clear();
    			closeList.clear();
    			// 开始搜索
    			openList.add(mapInfo.start);
    			moveNodes(mapInfo);
    		}
    	
    
    		/**
    		 * 移动当前结点
    		 */
    		private void moveNodes(MapInfo mapInfo)
    		{
    			while (!openList.isEmpty())
    			{
    				Node current = openList.poll();
    				closeList.add(current);
    				addNeighborNodeInOpen(mapInfo,current);
    				if (isCoordInClose(mapInfo.end.coord))
    				{
    					drawPath(mapInfo.maps, mapInfo.end);
    					break;
    				}
    			}
    		}
    		
    		/**
    		 * 在二维数组中绘制路径
    		 */
    		private void drawPath(int[][] maps, Node end)
    		{
    			if(end==null||maps==null) return;
    			System.out.println("总代价：" + end.G);
    			while (end != null)
    			{
    				Coord c = end.coord;
    				maps[c.y][c.x] = PATH;
    				end = end.parent;
    			}
    		}
    	
    
    		/**
    		 * 添加所有邻结点到open表
    		 */
    		private void addNeighborNodeInOpen(MapInfo mapInfo,Node current)
    		{
    			int x = current.coord.x;
    			int y = current.coord.y;
    			// 左
    			addNeighborNodeInOpen(mapInfo,current, x - 1, y, DIRECT_VALUE);
    			// 上
    			addNeighborNodeInOpen(mapInfo,current, x, y - 1, DIRECT_VALUE);
    			// 右
    			addNeighborNodeInOpen(mapInfo,current, x + 1, y, DIRECT_VALUE);
    			// 下
    			addNeighborNodeInOpen(mapInfo,current, x, y + 1, DIRECT_VALUE);
    			// 左上
    			addNeighborNodeInOpen(mapInfo,current, x - 1, y - 1, OBLIQUE_VALUE);
    			// 右上
    			addNeighborNodeInOpen(mapInfo,current, x + 1, y - 1, OBLIQUE_VALUE);
    			// 右下
    			addNeighborNodeInOpen(mapInfo,current, x + 1, y + 1, OBLIQUE_VALUE);
    			// 左下
    			addNeighborNodeInOpen(mapInfo,current, x - 1, y + 1, OBLIQUE_VALUE);
    		}
    	
    
    		/**
    		 * 添加一个邻结点到open表
    		 */
    		private void addNeighborNodeInOpen(MapInfo mapInfo,Node current, int x, int y, int value)
    		{
    			if (canAddNodeToOpen(mapInfo,x, y))
    			{
    				Node end=mapInfo.end;
    				Coord coord = new Coord(x, y);
    				int G = current.G + value; // 计算邻结点的G值
    				Node child = findNodeInOpen(coord);
    				if (child == null)
    				{
    					int H=calcH(end.coord,coord); // 计算H值
    					if(isEndNode(end.coord,coord))
    					{
    						child=end;
    						child.parent=current;
    						child.G=G;
    						child.H=H;
    					}
    					else
    					{
    						child = new Node(coord, current, G, H);
    					}
    					openList.add(child);
    				}
    				else if (child.G > G)
    				{
    					child.G = G;
    					child.parent = current;
    					openList.add(child);
    				}
    			}
    		}
    	
    
    		/**
    		 * 从Open列表中查找结点
    		 */
    		private Node findNodeInOpen(Coord coord)
    		{
    			if (coord == null || openList.isEmpty()) return null;
    			for (Node node : openList)
    			{
    				if (node.coord.equals(coord))
    				{
    					return node;
    				}
    			}
    			return null;
    		}
    	
    
    	
    
    		/**
    		 * 计算H的估值：“曼哈顿”法，坐标分别取差值相加
    		 */
    		private int calcH(Coord end,Coord coord)
    		{
    			return Math.abs(end.x - coord.x)
    					+ Math.abs(end.y - coord.y);
    		}
    		
    		/**
    		 * 判断结点是否是最终结点
    		 */
    		private boolean isEndNode(Coord end,Coord coord)
    		{
    			return coord != null && end.equals(coord);
    		}
    	
    
    		/**
    		 * 判断结点能否放入Open列表
    		 */
    		private boolean canAddNodeToOpen(MapInfo mapInfo,int x, int y)
    		{
    			// 是否在地图中
    			if (x < 0 || x >= mapInfo.width || y < 0 || y >= mapInfo.hight) return false;
    			// 判断是否是不可通过的结点
    			if (mapInfo.maps[y][x] == BAR) return false;
    			// 判断结点是否存在close表
    			if (isCoordInClose(x, y)) return false;
    	
    
    			return true;
    		}
    	
    
    		/**
    		 * 判断坐标是否在close表中
    		 */
    		private boolean isCoordInClose(Coord coord)
    		{
    			return coord!=null&&isCoordInClose(coord.x, coord.y);
    		}
    	
    
    		/**
    		 * 判断坐标是否在close表中
    		 */
    		private boolean isCoordInClose(int x, int y)
    		{
    			if (closeList.isEmpty()) return false;
    			for (Node node : closeList)
    			{
    				if (node.coord.x == x && node.coord.y == y)
    				{
    					return true;
    				}
    			}
    			return false;
    		}
    	}
    	
