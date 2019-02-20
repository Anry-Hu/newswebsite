var date=new Array(7);
var news=new Array(7);
var user=new Array(7);

window.onload=function(){

   for(var i=0;i<7;++i){
       date[6-i]=document.getElementById("date"+i.toString()).innerHTML;
       news[6-i]=parseInt(document.getElementById("news"+i.toString()).innerHTML);
       user[6-i]=parseInt(document.getElementById("user"+i.toString()).innerHTML);
   }


    var ctx1 = document.getElementById("myChart1").getContext("2d");
    var chart1 = new Chart(ctx1, {
        // The type of chart we want to create
        type: 'line',
        // The data for our dataset
        data: {
            labels: [date[0],date[1],date[2],date[3],date[4],date[5],date[6]],
            datasets: [
                {
                    backgroundColor: "transparent",//绘制双曲线的时候最好使用rgba,要不不透明的白色背景可以使得某些线条绘制不出来
                    borderColor: 'rgb(255, 99, 132)',
                    data: [news[0],news[1],news[2],news[3],news[4],news[5],news[6]]
                }
                ]
        },

        // 配置文件
        options: {
            //标题设置
            title:{
                display:false
            },
            //禁用动画
            animation:{
                duration:1000
            },
            hover:{
                animationDuration:500
            },
            responsiveAnimationDuration: 0,
            //提示功能
            tooltips:{
                enable:true
            },
            //顶部的文字提示
            legend:{
                display:false
            },
            //设置x,y轴网格线显示,标题等
            scales :{
                xAxes:[{
                    //轴标题
                    scaleLabel:{
                        display:true,
                        labelString:'时间（最近7天）',
                        fontColor:'#666'
                    },
                    //网格显示
                    gridLines:{
                        display:true
                    }
                }],
                yAxes:[{
                    scaleLabel:{
                        display:true,
                        labelString:'访问量/次'
                    },
                    gridLines:{
                        display:true
                    }
                }
                ]

            },

            //禁用赛尔曲线
            elements: {
                line: {
                    tension: 0
                }
            }

        }
    });

    var ctx2 = document.getElementById("myChart2").getContext("2d");
    var chart2 = new Chart(ctx2, {
        // The type of chart we want to create
        type: 'line',
        // The data for our dataset
        data: {
            labels: [date[0],date[1],date[2],date[3],date[4],date[5],date[6]],
            datasets: [
                {
                    backgroundColor: "transparent",//绘制双曲线的时候最好使用rgba,要不不透明的白色背景可以使得某些线条绘制不出来
                    borderColor: 'rgb(82,125,255)',
                    data: [user[0],user[1],user[2],user[3],user[4],user[5],user[6]]
                }
            ]
        },

        // 配置文件
        options: {
            //标题设置
            title:{
                display:false
            },
            //禁用动画
            animation:{
                duration:1000
            },
            hover:{
                animationDuration:500
            },
            responsiveAnimationDuration: 0,
            //提示功能
            tooltips:{
                enable:true
            },
            //顶部的文字提示
            legend:{
                display:false
            },
            //设置x,y轴网格线显示,标题等
            scales :{
                xAxes:[{
                    //轴标题
                    scaleLabel:{
                        display:true,
                        labelString:'时间（最近7天）',
                        fontColor:'#666'
                    },
                    //网格显示
                    gridLines:{
                        display:true
                    }
                }],
                yAxes:[{
                    scaleLabel:{
                        display:true,
                        labelString:'访问量/次'
                    },
                    gridLines:{
                        display:true
                    }
                }
                ]

            },

            //禁用赛尔曲线
            elements: {
                line: {
                    tension: 0
                }
            }

        }
    });


};


