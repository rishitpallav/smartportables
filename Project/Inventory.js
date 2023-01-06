google.charts.load('current', {packages: ['corechart', 'bar']});
// google.charts.setOnLoadCallback(drawStacked);

//Bind click event to make an ajax call to post request of Inventory. This will return
$("#btnGetChartData").click(function () {
     $("#btnGetChartData").hide();
    $.ajax({
        url: "Inventory",
        type: "POST",
        data: "{}",
        success: function (msg) {
            createDataTable(msg)            
        },
        error: function(){
            console.log("error occurred while making ajax call;")
        }
    });    
});


//This method will parse json data and build datatable required by google api to plot the bar chart.
function createDataTable(jsonData) {
    var parsedData = $.parseJSON(jsonData);
    var data = new Array();
    var orderNameArr = new Array();

    //Create an array of product name and an array of zipcodes
    for(var i=0; i<parsedData.length; i++) {
        var orderName = parsedData[i]["name"];
        if(!orderNameArr.includes(orderName)) {
            orderNameArr.push(orderName);
        }
     }

     console.log(orderNameArr);

     //Create header array for google api
     var headingArray = new Array(orderNameArr.length+1);
     headingArray[0] = "Product Name";
     var j=0;

     for(var i=1; i<=orderNameArr.length; i++) {
        headingArray[i] = orderNameArr[j]; 
        j++;
     }

     data[0] = headingArray;
     var m =1;
    
     //Loop through jsondata and create an array of arrays to plot the chart;
    
        var dataArr = new Array(headingArray.length);
        for(var j=0; j<orderNameArr.length; j++) {
            for(k=0; k<parsedData.length; k++) {
                if(parsedData[k]["name"] === orderNameArr[j]) {                    
                    dataArr[j+1] = parseInt(parsedData[k]["available"]);                   
                }                 
            }

        }
        
        //Set empty cell elements to zero;
        for(var n=1; n<headingArray.length; n++) {
            if(!(dataArr[n] > 0)) {
                dataArr[n] = 0;
            }
        }
        data[m] = (dataArr);
        m++;
        console.log(data);
     drawChart(data, orderNameArr);
}

//Plot the chart using 2d array and product names as subtitles;
function drawChart(data, orderNameArr) {
    var orderNames = "";
    for(var i=0; i<orderNameArr.length; i++) {
        orderNames += orderNameArr[i] + ",";
    }


    //Invoke google's built in method to get data table object required by google.
     var chartData = google.visualization.arrayToDataTable(data);

     var options = {
        'width':500,
        'height':1000,
          chart: {
            title: 'All Products Chart',
            subtitle: orderNames,
          },
          bars: 'horizontal' // Required for Material Bar Charts.
        };

    var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
    chart.draw(chartData, options);
}




