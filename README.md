# How Time Flies(时间都去哪了)

## 配置文件

模拟器：Nexus 5X API 24,5.2英寸,1080*1920,420dpi

minAPI:API17(太低版本的使用不了XUI)

页面框架：页面框架：[XUI](https://blog.csdn.net/weixin_39253892/article/details/100145339?depth_1-)，感觉这个比官方文档写的详细

活动切换动画:暂定使用系统自带的， [参考文档](https://blog.csdn.net/lpCrazyBoy/article/details/83060096)

视图绑定框架：Butter Knife

图表库：[MPAndroidChart](https://www.jianshu.com/p/f1cfdf2dc98c), GitHub上的说明文档打不开,这个文档写的能凑合. [针对饼图,这个感觉讲的更好](https://blog.csdn.net/qq_43332570/article/details/103253320?depth_1-)


## 资源文件

### 图片位置：drawable-v24
home_page_bg:第一次进入时背景

login_bg:登录界面背景

register_bg:注册界面背景

### 布局：layout
home_page_layout:第一次进入时布局设置

login_page_layout:登录界面，上面的图标还没完全搞定

register_page_layout:注册界面，上面的图标还没完全搞定

show_time_page_layout:显示使用时长的界面,目前只设置了饼图

test:自己测试一些展示效果

##  src文件

### base
BaseActivity:基础活动比如XUI的绑定、设置默认动画切换样式，供所有活动继承，具体怎么用不懂照搬学长当时给的模板
### activity
HomeActivity:首页，只实现单独的演示功能，暂时未深入

LoginActivity:登录，只实现单独的演示功能，暂时未深入

RegisterActivity:注册，只实现单独的演示功能，暂时未深入

ShowTimeActivity(为了测试方便,目前在AndroidManifest.xml中将其声明的主页):使用时长,目前只设置了饼图的展示效果和测试数据
### build.gradle(Module:app)
新增加了MPAndroidChart的依赖