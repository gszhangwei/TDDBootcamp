# TDDBootcamp

Parking Lot System

#1
| Given | When | Then|
|:-------|:------:|:-----:|
|有一辆车来了（有空余车位），车牌不重复|停车	|出票，停车成功|
|有一辆车来了（有空余车位），无车牌|停车|不出票，停车失败|
|有一辆车来了，有空余车位，车牌重复|停车|不出票，停车失败|
|有一辆车来了（没有空余车位）|停车|不出票，停车失败|
|用户拿正确的票来取车（有相应的车）|取车|取车成功|
|用户拿错误的票来取车（没有相应的车）|取车|取车失败|

#2
| Given | When | Then|
|:-------|:------:|:-----:|
|有两个停车场A，B，有一辆车来了，车牌不重复，A有空余车位|	停车	|出票，停车到A停车场|
|有两个停车场A，B，有一辆车来了，车牌不重复，A没有空余车位，B有空余车位|	停车	|出票，停车到B停车场|
|有两个停车场A，B，有一辆车来了，无车牌，A和B有空余车位|	停车|	不出票，停车失败|
|有一辆车来了，车牌和A里面某辆车重复，A和B有空余车位	|停车	|不出票，停车失败|
|有一辆车来了，车牌和B里面某辆车重复，A和B有空余车位	|停车	|不出票，停车失败|
|有一辆车来了，车牌不重复，A和B都没有空余车位	|停车	|不出票，停车失败|
|用户拿正确的票来取车，且有相应的车	|取车	|取车成功|
|用户拿错误的票来取车（没有相应的车）|	取车	|取车失败|

#3
| Given | When | Then|
|:-------|:------:|:-----:|
|有两个停车场A-有空余，B-有空余，有一辆车来了，车牌不重复，B空余车位较多| 停车 | 出票，停车到A停车场|
|有两个停车场A，B，有一辆车来了，车牌不重复，A没有空余车位，B有空余车位|	停车	|出票，停车到B停车场|
|有两个停车场A，B，有一辆车来了，无车牌，A和B有空余车位|	停车|	不出票，停车失败|
|有一辆车来了，车牌和A里面某辆车重复，A和B有空余车位	|停车	|不出票，停车失败|
|有一辆车来了，车牌和B里面某辆车重复，A和B有空余车位	|停车	|不出票，停车失败|
|有一辆车来了，车牌不重复，A和B都没有空余车位	|停车	|不出票，停车失败|
|用户拿正确的票来取车，且有相应的车	|取车	|取车成功|
|用户拿错误的票来取车（没有相应的车）|	取车	|取车失败|