package class164;

// 边权上限内第k大点权，C++版
// 图里有n个点，m条无向边，点有点权，边有边权，图里可能有若干个连通的部分
// 一共有q条查询，查询格式如下
// 查询 u x k : 从点u开始，只能走过权值<=x的边
//              所有能到达的点中，打印第k大点权，如果不存在打印-1
// 1 <= n <= 10^5
// 0 <= m、q <= 5 * 10^5
// 1 <= 点权、边权 <= 10^9
// 本题要求强制在线，具体规定请打开测试链接查看
// 测试链接 : https://www.luogu.com.cn/problem/P7834
// 如下实现是C++的版本，C++版本和java版本逻辑完全一样
// 提交如下代码，可以通过所有测试用例

//#include <bits/stdc++.h>
//
//using namespace std;
//
//struct Edge {
//    int u, v, w;
//};
//
//bool cmp(Edge x, Edge y) {
//    return x.w < y.w;
//}
//
//const int MAXN = 100001;
//const int MAXK = 200001;
//const int MAXM = 500001;
//const int MAXT = MAXN * 40;
//const int MAXH = 20;
//int n, m, q;
//int node[MAXN];
//int sorted[MAXN];
//int diff;
//Edge edge[MAXM];
//
//int father[MAXK];
//
//int head[MAXK];
//int nxt[MAXK];
//int to[MAXK];
//int cntg;
//int nodeKey[MAXK];
//int cntu;
//
//int dfn[MAXK];
//int seg[MAXK];
//int stjump[MAXK][MAXH];
//int siz[MAXK];
//int cntd;
//
//int root[MAXK];
//int ls[MAXT];
//int rs[MAXT];
//int segsiz[MAXT];
//int cntt;
//
//int kth(int num) {
//    int left = 1, right = diff;
//    while (left <= right) {
//        int mid = (left + right) / 2;
//        if (sorted[mid] == num) {
//            return mid;
//        } else if (sorted[mid] < num) {
//            left = mid + 1;
//        } else {
//            right = mid - 1;
//        }
//    }
//    return -1;
//}
//
//void prepare() {
//    for (int i = 1; i <= n; i++) {
//        sorted[i] = node[i];
//    }
//    sorted[n + 1] = 0;
//    sort(sorted + 1, sorted + n + 2);
//    diff = 1;
//    for (int i = 2; i <= n + 1; i++) {
//        if (sorted[diff] != sorted[i]) {
//            sorted[++diff] = sorted[i];
//        }
//    }
//    for (int i = 1; i <= n; i++) {
//        node[i] = kth(node[i]);
//    }
//}
//
//void addEdge(int u, int v) {
//    nxt[++cntg] = head[u];
//    to[cntg] = v;
//    head[u] = cntg;
//}
//
//int find(int i) {
//    if (i != father[i]) {
//        father[i] = find(father[i]);
//    }
//    return father[i];
//}
//
//void kruskalRebuild() {
//    for (int i = 1; i <= n; i++) {
//        father[i] = i;
//    }
//    sort(edge + 1, edge + m + 1, cmp);
//    cntu = n;
//    for (int i = 1, fx, fy; i <= m; i++) {
//        fx = find(edge[i].u);
//        fy = find(edge[i].v);
//        if (fx != fy) {
//            father[fx] = father[fy] = ++cntu;
//            father[cntu] = cntu;
//            nodeKey[cntu] = edge[i].w;
//            addEdge(cntu, fx);
//            addEdge(cntu, fy);
//        }
//    }
//}
//
//void dfs(int u, int fa) {
//    siz[u] = 1;
//    dfn[u] = ++cntd;
//    seg[cntd] = u;
//    stjump[u][0] = fa;
//    for (int p = 1; p < MAXH; p++) {
//        stjump[u][p] = stjump[ stjump[u][p - 1] ][p - 1];
//    }
//    for (int e = head[u]; e > 0; e = nxt[e]) {
//        dfs(to[e], u);
//    }
//    for (int e = head[u]; e > 0; e = nxt[e]) {
//        siz[u] += siz[to[e]];
//    }
//}
//
//int build(int l, int r) {
//    int rt = ++cntt;
//    segsiz[rt] = 0;
//    if (l < r) {
//        int mid = (l + r) / 2;
//        ls[rt] = build(l, mid);
//        rs[rt] = build(mid + 1, r);
//    }
//    return rt;
//}
//
//int insert(int jobi, int l, int r, int i) {
//    int rt = ++cntt;
//    ls[rt] = ls[i];
//    rs[rt] = rs[i];
//    segsiz[rt] = segsiz[i] + 1;
//    if (l < r) {
//        int mid = (l + r) / 2;
//        if (jobi <= mid) {
//            ls[rt] = insert(jobi, l, mid, ls[rt]);
//        } else {
//            rs[rt] = insert(jobi, mid + 1, r, rs[rt]);
//        }
//    }
//    return rt;
//}
//
//int query(int jobk, int l, int r, int pre, int post) {
//    if (l == r) {
//        return l;
//    }
//    int mid = (l + r) / 2;
//    int rsize = segsiz[ rs[post] ] - segsiz[ rs[pre] ];
//    if (rsize >= jobk) {
//        return query(jobk, mid + 1, r, rs[pre], rs[post]);
//    } else {
//        return query(jobk - rsize, l, mid, ls[pre], ls[post]);
//    }
//}
//
//int compute(int u, int x, int k) {
//    for (int p = MAXH - 1; p >= 0; p--) {
//        if (stjump[u][p] > 0 && nodeKey[stjump[u][p]] <= x) {
//            u = stjump[u][p];
//        }
//    }
//    int idx = query(k, 1, diff, root[dfn[u] - 1], root[dfn[u] + siz[u] - 1]);
//    return sorted[idx];
//}
//
//int main(){
//    ios::sync_with_stdio(false);
//    cin.tie(nullptr);
//    cin >> n >> m >> q;
//    for (int i = 1; i <= n; i++) {
//        cin >> node[i];
//    }
//    for (int i = 1; i <= m; i++) {
//        cin >> edge[i].u >> edge[i].v >> edge[i].w;
//    }
//    prepare();
//    kruskalRebuild();
//    for (int i = 1; i <= cntu; i++) {
//        if (i == father[i]) {
//            dfs(i, 0);
//        }
//    }
//    root[0] = build(1, diff);
//    for (int i = 1; i <= cntd; i++) {
//        if (seg[i] <= n) {
//            root[i] = insert(node[seg[i]], 1, diff, root[i - 1]);
//        } else {
//            root[i] = root[i - 1];
//        }
//    }
//    for (int i = 1, u, x, k, lastAns = 0; i <= q; i++) {
//        cin >> u >> x >> k;
//        u = ((u ^ lastAns) % n) + 1;
//        x = x ^ lastAns;
//        k = ((k ^ lastAns) % n) + 1;
//        lastAns = compute(u, x, k);
//        if (lastAns == 0) {
//            cout << -1 << "\n";
//        } else {
//            cout << lastAns << "\n";
//        }
//    }
//    return 0;
//}