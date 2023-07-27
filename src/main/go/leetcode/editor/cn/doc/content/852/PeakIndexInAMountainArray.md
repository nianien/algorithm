符合下列属性的数组 <code>arr</code> 称为 <strong>山脉数组</strong> ：

<ul> 
 <li><code>arr.length &gt;= 3</code></li> 
 <li>存在 <code>i</code>（<code>0 &lt; i&nbsp;&lt; arr.length - 1</code>）使得： 
  <ul> 
   <li><code>arr[0] &lt; arr[1] &lt; ... arr[i-1] &lt; arr[i] </code></li> 
   <li><code>arr[i] &gt; arr[i+1] &gt; ... &gt; arr[arr.length - 1]</code></li> 
  </ul> </li> 
</ul>

<p>给你由整数组成的山脉数组 <code>arr</code> ，返回满足 <code>arr[0] &lt; arr[1] &lt; ... arr[i - 1] &lt; arr[i] &gt; arr[i + 1] &gt; ... &gt; arr[arr.length - 1]</code> 的下标 <code>i</code> 。</p>

<p>你必须设计并实现时间复杂度为 <code>O(log(n))</code> 的解决方案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [0,1,0]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [0,2,1,0]
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [0,10,5,2]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>3 &lt;= arr.length &lt;= 10<sup>5</sup></code></li> 
 <li><code>0 &lt;= arr[i] &lt;= 10<sup>6</sup></code></li> 
 <li>题目数据保证 <code>arr</code> 是一个山脉数组</li> 
</ul>

<div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 358</li><li>👎 0</li></div>