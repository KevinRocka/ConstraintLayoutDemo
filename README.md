# ConstraintLayoutDemo
Android 约束布局示例

约束布局在2016年IO 谷歌大会早就提出来了，As 2.2早也就有了可以拖拽的拓展，现在As 都3.x了，我现在才来看这个布局也是尴尬。ConstraintLayout有效的解决了多层嵌套复杂布局的问题，加快渲染速度，有点类似与RelativeLayout和LinearLayout的集合，但是远比RelativeLayout和LinearLayout强大。

<!-- more -->

> 本文建议的是通过手写代码的方式来构造布局，等各个属性熟悉一些了再进行拖拽来减少工作量，加快开发速度。

先看看效果图吧：
![image](https://kevinrocka.github.io/2018/05/16/android_constraintlayout/0.png)

### Normal(基础属性)
 
* 解析主要属性：其实也是使用的主要方法，按照这个伪代码去理解就很简单了，套用即可，翻译过来的意思就是：将所需视图的A部位与view2的B部位对齐。

	```java
	<TextView
	        android:id="@+id/view1"
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        app:layout_constraintA_toBOf="@+id/view2"/>
	```
	
* ConstraintLayout 的这些属性是为控件添加了某个方向的约束力，根据某个方向约束力的“有无”或“强弱”，控件会位于不同的位置。当设置了基础属性不起作用时候，可以复查是不是约束力不够，也就是其他方向上没有绑定约束。

* 示例代码与图例

	示例代码：

	```java
	<?xml version="1.0" encoding="utf-8"?>
	<android.support.constraint.ConstraintLayout
	    xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:app="http://schemas.android.com/apk/res-auto"
	    xmlns:tools="http://schemas.android.com/tools"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    tools:context=".MainActivity">
	
	    <Button
	        android:id="@+id/center"
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        app:layout_constraintLeft_toLeftOf="parent"
	        app:layout_constraintRight_toRightOf="parent"
	        app:layout_constraintTop_toTopOf="parent"
	        app:layout_constraintBottom_toBottomOf="parent"
	        android:text="center"/>
	
	    <Button
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:text="Left"
	        app:layout_constraintTop_toTopOf="parent"
	        app:layout_constraintBottom_toBottomOf="parent"
	        app:layout_constraintRight_toLeftOf="@+id/center"/>
	
	    <Button
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:text="Right"
	        app:layout_constraintTop_toTopOf="parent"
	        app:layout_constraintBottom_toBottomOf="parent"
	        app:layout_constraintLeft_toRightOf="@+id/center"/>
	
	    <Button
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:text="Top"
	        app:layout_constraintLeft_toLeftOf="parent"
	        app:layout_constraintRight_toRightOf="parent"
	        app:layout_constraintBottom_toTopOf="@+id/center"/>
	
	    <Button
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:text="Bottom"
	        app:layout_constraintLeft_toLeftOf="parent"
	        app:layout_constraintRight_toRightOf="parent"
	        app:layout_constraintTop_toBottomOf="@+id/center"/>
	
	</android.support.constraint.ConstraintLayout>
	```
	图例：
	![image](https://kevinrocka.github.io/2018/05/16/android_constraintlayout/1.png)
	
### Bias(偏斜)

* 通过设置app:layout_constraintHorizontal_bias和app:layout_constraintVertical_bias=""来控制约束的整体便宜量，除开控件自生的宽高来便宜位置。

* 示例代码与效果图
    
    示例代码：

    ```java
    <?xml version="1.0" encoding="utf-8"?>
    <android.support.constraint.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">
    
        <Button
            android:id="@+id/tv_1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Center"
            app:layout_constraintVertical_bias="0.3"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintTop_toTopOf="parent"/>
    
        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Test for visible"
            app:layout_constraintBottom_toBottomOf="@+id/tv_1"
            app:layout_constraintLeft_toLeftOf="@id/tv_1"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"/>
    
    
    </android.support.constraint.ConstraintLayout>
    ```
    图例：![image](https://kevinrocka.github.io/2018/05/16/android_constraintlayout/2.png)
    
    
### margin与GoneMargin

* ConstraintLayout布局中margin与以前的一样只是不能为负值。
* 如果要在一个方向上设置margin，则该方向上必须有约束，否则不生效
* GoneMargin的作用主要是：一旦某个方向上的约束view不可以见，这时如果设置了该属性，该方向将自动增加margin值。即目标必须不可见


### Chains(链)

* 主要通过设置三个属性
	1. spread_inside:两边不留空间，中间间距平分
	2. spread:完全均分
	3. packed:完全不留间距
* 要使用chain效果，他们之间必须完全相互约束，同时chain style的设置都是以第一个view为基点。同时chain style默认值为spread。
* 官方图例解释几个用法：
	![image](https://kevinrocka.github.io/2018/05/16/android_constraintlayout/3_1.png)
* 示例与效果图
	
	示例代码：
	
	```java
	<?xml version="1.0" encoding="utf-8"?>
	<android.support.constraint.ConstraintLayout
	    xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:app="http://schemas.android.com/apk/res-auto"
	    xmlns:tools="http://schemas.android.com/tools"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    tools:context=".MainActivity">
	
	    <TextView
	        android:id="@+id/tv1"
	        android:layout_width="wrap_content"
	        android:layout_height="50dp"
	        android:layout_marginTop="10dp"
	        android:background="#B22222"
	        android:gravity="center"
	        android:text="Normal_1"
	        app:layout_constraintBottom_toTopOf="@+id/spread_tv1"
	        app:layout_constraintLeft_toLeftOf="parent"
	        app:layout_constraintRight_toLeftOf="@+id/tv2"
	        app:layout_constraintTop_toTopOf="parent"/>
	
	    <TextView
	        android:id="@+id/tv2"
	        android:layout_width="wrap_content"
	        android:layout_height="50dp"
	        android:background="#87CEEB"
	        android:gravity="center"
	        android:text="Normal_2"
	        app:layout_constraintBottom_toTopOf="@+id/spread_tv2"
	        app:layout_constraintLeft_toRightOf="@+id/tv1"
	        app:layout_constraintRight_toLeftOf="@+id/tv3"
	        app:layout_constraintTop_toTopOf="parent"
	        />
	
	    <TextView
	        android:id="@+id/tv3"
	        android:layout_width="wrap_content"
	        android:layout_height="50dp"
	        android:layout_marginTop="10dp"
	        android:background="#00FF00"
	        android:gravity="center"
	        android:text="Normal_3"
	        app:layout_constraintLeft_toRightOf="@+id/tv2"
	        app:layout_constraintRight_toRightOf="parent"
	        app:layout_constraintTop_toTopOf="parent"/>
	
	    <!-- spread -->
	    <TextView
	        android:id="@+id/spread_tv1"
	        android:layout_width="0dp"
	        android:layout_height="50dp"
	        android:layout_marginTop="10dp"
	        android:background="#B22222"
	        android:gravity="center"
	        android:text="Spread_1"
	        app:layout_constraintHorizontal_chainStyle="spread"
	        app:layout_constraintLeft_toLeftOf="parent"
	        app:layout_constraintRight_toLeftOf="@+id/spread_tv2"
	        app:layout_constraintTop_toBottomOf="@+id/tv1"/>
	
	    <TextView
	        android:id="@+id/spread_tv2"
	        android:layout_width="0dp"
	        android:layout_height="50dp"
	        android:background="#87CEEB"
	        android:gravity="center"
	        android:text="Spread_2"
	        app:layout_constraintLeft_toRightOf="@+id/spread_tv1"
	        app:layout_constraintRight_toLeftOf="@+id/spread_tv3"
	        app:layout_constraintTop_toTopOf="@+id/spread_tv1"/>
	
	
	    <TextView
	        android:id="@+id/spread_tv3"
	        android:layout_width="wrap_content"
	        android:layout_height="50dp"
	        android:background="#00FF00"
	        android:gravity="center"
	        android:text="Spread_3"
	        app:layout_constraintLeft_toRightOf="@+id/spread_tv2"
	        app:layout_constraintRight_toRightOf="parent"
	        app:layout_constraintTop_toTopOf="@+id/spread_tv1"/>
	
	    <!-- spread_inside -->
	    <TextView
	        android:id="@+id/spread_inside_tv1"
	        android:layout_width="wrap_content"
	        android:layout_height="50dp"
	        android:layout_marginTop="10dp"
	        android:background="#B22222"
	        android:gravity="center"
	        android:text="Spread inside_1"
	        app:layout_constraintHorizontal_chainStyle="spread_inside"
	        app:layout_constraintLeft_toLeftOf="parent"
	        app:layout_constraintRight_toLeftOf="@+id/spread_inside_tv2"
	        app:layout_constraintTop_toBottomOf="@+id/spread_tv1"/>
	
	    <TextView
	        android:id="@+id/spread_inside_tv2"
	        android:layout_width="wrap_content"
	        android:layout_height="50dp"
	        android:background="#87CEEB"
	        android:gravity="center"
	        android:text="Spread inside_2"
	        app:layout_constraintLeft_toRightOf="@+id/spread_inside_tv1"
	        app:layout_constraintRight_toLeftOf="@+id/spread_inside_tv3"
	        app:layout_constraintTop_toTopOf="@+id/spread_inside_tv1"/>
	
	
	    <TextView
	        android:id="@+id/spread_inside_tv3"
	        android:layout_width="wrap_content"
	        android:layout_height="50dp"
	        android:background="#00FF00"
	        android:gravity="center"
	        android:text="Spread inside_3"
	        app:layout_constraintLeft_toRightOf="@+id/spread_inside_tv2"
	        app:layout_constraintRight_toRightOf="parent"
	        app:layout_constraintTop_toTopOf="@+id/spread_inside_tv1"/>
	
	    <!-- pack -->
	    <TextView
	        android:id="@+id/packed_tv1"
	        android:layout_width="wrap_content"
	        android:layout_height="50dp"
	        android:layout_marginTop="10dp"
	        android:background="#B22222"
	        android:gravity="center"
	        android:text="Packed_1"
	        app:layout_constraintHorizontal_chainStyle="packed"
	        app:layout_constraintLeft_toLeftOf="parent"
	        app:layout_constraintRight_toLeftOf="@+id/packed_tv2"
	        app:layout_constraintTop_toBottomOf="@+id/spread_inside_tv1"/>
	
	    <TextView
	        android:id="@+id/packed_tv2"
	        android:layout_width="wrap_content"
	        android:layout_height="50dp"
	        android:background="#87CEEB"
	        android:gravity="center"
	        android:text="Packed_2"
	        app:layout_constraintLeft_toRightOf="@+id/packed_tv1"
	        app:layout_constraintRight_toLeftOf="@+id/packed_tv3"
	        app:layout_constraintTop_toTopOf="@+id/packed_tv1"/>
	
	
	    <TextView
	        android:id="@+id/packed_tv3"
	        android:layout_width="wrap_content"
	        android:layout_height="50dp"
	        android:background="#00FF00"
	        android:gravity="center"
	        android:text="Packed_3"
	        app:layout_constraintLeft_toRightOf="@+id/packed_tv2"
	        app:layout_constraintRight_toRightOf="parent"
	        app:layout_constraintTop_toTopOf="@+id/packed_tv1"/>
	
	    <!-- packed with bias -->
	    <TextView
	        android:id="@+id/packed_bias_tv1"
	        android:layout_width="wrap_content"
	        android:layout_height="50dp"
	        android:layout_marginTop="10dp"
	        android:background="#B22222"
	        android:gravity="center"
	        android:text="Packed_bias_1"
	        app:layout_constraintHorizontal_chainStyle="packed"
	        app:layout_constraintHorizontal_bias="0.1"
	        app:layout_constraintLeft_toLeftOf="parent"
	        app:layout_constraintRight_toLeftOf="@+id/packed_bias_tv2"
	        app:layout_constraintTop_toBottomOf="@+id/packed_tv1"/>
	
	    <TextView
	        android:id="@+id/packed_bias_tv2"
	        android:layout_width="wrap_content"
	        android:layout_height="50dp"
	        android:background="#87CEEB"
	        android:gravity="center"
	        android:text="Packed_bias_2"
	        app:layout_constraintLeft_toRightOf="@+id/packed_bias_tv1"
	        app:layout_constraintRight_toLeftOf="@+id/packed_bias_tv3"
	        app:layout_constraintTop_toTopOf="@+id/packed_bias_tv1"/>
	
	
	    <TextView
	        android:id="@+id/packed_bias_tv3"
	        android:layout_width="wrap_content"
	        android:layout_height="50dp"
	        android:background="#00FF00"
	        android:gravity="center"
	        android:text="Packed_bias_3"
	        app:layout_constraintLeft_toRightOf="@+id/packed_bias_tv2"
	        app:layout_constraintRight_toRightOf="parent"
	        app:layout_constraintTop_toTopOf="@+id/packed_bias_tv1"/>
	
	    <!-- weight chain -->
	    <TextView
	        android:id="@+id/weight_tv1"
	        android:layout_width="0dp"
	        android:layout_height="50dp"
	        android:layout_marginTop="10dp"
	        android:background="#B22222"
	        android:gravity="center"
	        android:text="weight_1"
	        app:layout_constraintHorizontal_weight="1"
	        app:layout_constraintHorizontal_chainStyle="packed"
	        app:layout_constraintHorizontal_bias="0.1"
	        app:layout_constraintLeft_toLeftOf="parent"
	        app:layout_constraintRight_toLeftOf="@+id/weight_tv2"
	        app:layout_constraintTop_toBottomOf="@+id/packed_bias_tv1"/>
	
	    <TextView
	        android:id="@+id/weight_tv2"
	        android:layout_width="0dp"
	        android:layout_height="50dp"
	        android:background="#87CEEB"
	        android:gravity="center"
	        android:text="weight_2"
	        app:layout_constraintHorizontal_weight="1"
	        app:layout_constraintLeft_toRightOf="@+id/weight_tv1"
	        app:layout_constraintRight_toLeftOf="@+id/weight_tv3"
	        app:layout_constraintTop_toTopOf="@+id/weight_tv1"/>
	
	
	    <TextView
	        android:id="@+id/weight_tv3"
	        android:layout_width="0dp"
	        android:layout_height="50dp"
	        android:background="#00FF00"
	        android:gravity="center"
	        android:text="weight_3"
	        app:layout_constraintHorizontal_weight="1"
	        app:layout_constraintLeft_toRightOf="@+id/weight_tv2"
	        app:layout_constraintRight_toRightOf="parent"
	        app:layout_constraintTop_toTopOf="@+id/weight_tv1"/>
	
	</android.support.constraint.ConstraintLayout>
	```

	效果图:
	![image](https://kevinrocka.github.io/2018/05/16/android_constraintlayout/3_2.png)
	
### Ratio(比例/率)
* 通过设置 app:layout_constraintDimensionRatio 属性来设置宽高比，需要至少设置宽度或者高度为 0dp，宽高的尺寸比例可以通过“float值”或者“宽度：高度”的形式来设置，如果宽度和高度都是 0dp ，系统会使用满足所有约束条件和宽高比率值的最大尺寸
如果要根据其中一个尺寸来约束另外一个尺寸，则可以在比率值的前面添加 W 或者 H 来指明约束宽度或者高度

* 示例与效果图

	示例代码：
	
	```java
	<?xml version="1.0" encoding="utf-8"?>
	<android.support.constraint.ConstraintLayout
	    xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:app="http://schemas.android.com/apk/res-auto"
	    xmlns:tools="http://schemas.android.com/tools"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    tools:context=".MainActivity">
	
	    <TextView
	        android:id="@+id/tv_1"
	        android:layout_width="0dp"
	        android:layout_height="wrap_content"
	        android:layout_marginTop="20dp"
	        android:background="@color/colorPrimaryDark"
	        android:text="This is a normal text !"
	        android:textColor="#ffffff"
	        app:layout_constraintDimensionRatio="10:1"
	        app:layout_constraintTop_toTopOf="parent"/>
	
	    <TextView
	        android:id="@+id/tv_2"
	        android:layout_width="0dp"
	        android:layout_height="0dp"
	        android:layout_marginTop="20dp"
	        android:background="@color/colorPrimaryDark"
	        android:text="This is a second text !"
	        android:textColor="#ffffff"
	        app:layout_constraintDimensionRatio="H,4:3"
	        app:layout_constraintLeft_toLeftOf="parent"
	        app:layout_constraintRight_toRightOf="parent"
	        app:layout_constraintBottom_toBottomOf="parent"
	        app:layout_constraintTop_toTopOf="parent"/>
	    
	</android.support.constraint.ConstraintLayout>
	```
	效果图：
    ![image](https://kevinrocka.github.io/2018/05/16/android_constraintlayout/4.png)

### Circle(角度约束)
* 主要属性：
  1. layout_constraintCircle: 代表约束的view的id
  2. layout_constraintCircleAngle: 代表约束的角度
  3. layout_constraintCircleRadius: 代表约束的半径大小
* 示例与效果图
    
    示例代码：

    ```java
    <?xml version="1.0" encoding="utf-8"?>
    <android.support.constraint.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">
    
        <Button
            android:id="@+id/tv_1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Center"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintTop_toTopOf="parent"/>
    
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="12"
            android:textColor="#345466"
            app:layout_constraintCircleAngle="60"
            app:layout_constraintCircle="@id/tv_1"
            app:layout_constraintCircleRadius="60dp"/>
    
    
    </android.support.constraint.ConstraintLayout>  
    ```
    效果图：
    ![image](https://kevinrocka.github.io/2018/05/16/android_constraintlayout/5.png)

### GuideLine(参照线)
* 它是一个参考线，他不会在界面显示
* 通过属性来设置横纵android:orientation="horizontal | vertical"
* 主要属性
    1. layout_constraintGuide_begin: 代表距离GuideLine左边或者底部的距离
    2. layout_constraintGuide_end: 代表距离GuideLine右边或者底部的距离
   3. layout_constraintGuide_percent：代表GuideLine相对于parent的位置百分比
* 示例代码与效果图

    示例代码：
    
    ```java
    <?xml version="1.0" encoding="utf-8"?>
    <android.support.constraint.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">
    
        <android.support.constraint.Guideline
            android:id="@+id/g1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:layout_constraintGuide_begin="300dp"
            android:orientation="vertical"/>
    
        <android.support.constraint.Guideline
            android:id="@+id/g2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:layout_constraintGuide_begin="200dp"
            android:orientation="horizontal"/>
    
        <Button
            android:id="@+id/tv_1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Center"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="@+id/g1"
            app:layout_constraintBottom_toBottomOf="@id/g2"
            app:layout_constraintTop_toTopOf="parent"/>
    
    
    </android.support.constraint.ConstraintLayout>
    ```
    效果图：
    ![image](https://kevinrocka.github.io/2018/05/16/android_constraintlayout/6.png)

### Barrier(参照视图)
* Barrier与GuideLine有点类似，也是布局不可见的，不同的是它就约束对象的
* 主要属性：
    1. barrierDirection：控制其方向
    2. constraint_referenced_ids：约束的view的参考id
* 主要是用来控制长度不可预期的约束与参展，tv_1,tv_2都是长度不可预期，但是tv_3始终是依附在tv_1和tv_2的右边。
* 示例代码与效果图

    示例代码:
        
    ```java
    <?xml version="1.0" encoding="utf-8"?>
    <android.support.constraint.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">
    
        <TextView
            android:id="@+id/tv_1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="20dp"
            android:background="@color/colorPrimaryDark"
            android:text="This is a normal text !"
            app:layout_constraintTop_toTopOf="parent"/>
    
        <TextView
            android:id="@+id/tv_2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="20dp"
            android:background="@color/colorAccent"
            android:text="This might be a long text..."
            app:layout_constraintTop_toBottomOf="@+id/tv_1"/>
    
        <android.support.constraint.Barrier
            android:id="@+id/barrier"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:barrierDirection="end"
            app:constraint_referenced_ids="tv_1,tv_2"/>
    
        <TextView
            android:id="@+id/tv_3"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:background="@color/colorAccent"
            android:layout_marginTop="15dp"
            android:layout_marginLeft="5dp"
            app:layout_constraintLeft_toRightOf="@id/barrier"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            android:ellipsize="end"
            android:text="This is the biggest long txtxxxxxThis is the biggest long txtxxxxxThis is the biggest long txtxxxxxThis is the biggest long txtxxxxxThis is the biggest long txtxxxxxThis is the biggest long txtxxxxx"/>
    
    </android.support.constraint.ConstraintLayout>
    ```
    效果图：
    ![image](https://kevinrocka.github.io/2018/05/16/android_constraintlayout/7.png)

### 其他
* 该项目Github地址 -> [点我](https://github.com/KevinRocka/ConstraintLayoutDemo)
* 谷歌官方出品解析ConstraintLayout的性能优势 -> [点我](https://mp.weixin.qq.com/s/gGR2itbY7hh9fo61SxaMQQ)