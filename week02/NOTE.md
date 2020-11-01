学习笔记
关于HashMap的小总结
1.参数概念
默认初始化容量 16
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
最大容量 2的30次方
static final int MAXIMUM_CAPACITY = 1 << 30;
默认的负载因子 0.75
static final float DEFAULT_LOAD_FACTOR = 0.75f;
当某个桶的数量大于8时，会转化为红黑树。
static final int TREEIFY_THRESHOLD = 8;
当某个桶节点数量小于6时，会转换为链表，前提是它当前是红黑树结构。
static final int UNTREEIFY_THRESHOLD = 6;
当整个hashMap中元素数量大于64时，也会进行转为红黑树结构。
static final int MIN_TREEIFY_CAPACITY = 64;
2.基本概念
Node<K,V>[] tab; Node<K,V> 数组
Node<K,V> node类

static class Node<K,V> implements Map.Entry<K,V> {
    final int hash;                              //hash值
    final K key;								  //map的key值
    V value;                                    //map的value值
    Node<K,V> next;                     //下一个NODE

    Node(int hash, K key, V value, Node<K,V> next) {                构造函数初始化
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }
}

3.put方法分析
final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
         如果表不存在或者标的长度为0，就进行扩容
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
            计算index，如果不存在node就在该点上创建node
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            如果key值一样，直接覆盖该值
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
                判断是否为红黑树
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
                判断是否为链表
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        如果链表的值大于8，就转为红黑树
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    key值相等就覆盖
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        超过最大容量就扩容
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
}

