google.charts.load('current', {packages: ['corechart', 'bar']});
// google.charts.setOnLoadCallback(drawStacked);

//Bind click event to make an ajax call to post request of DataVisualization. This will return
// a json object with top 3 review for each city;
$("#btnGetChartData").click(function () {
     $("#btnGetChartData").hide();
    $.ajax({
        url: "DataVisualization",
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
    var zipCodeArr = new Array();
    var dict = {};

    //Create an array of product name and an array of zipcodes
    for(var i=0; i<parsedData.length; i++) {
        var orderName = parsedData[i]["orderName"];
        var zipCode = parsedData[i]["orderName"];
        if(!orderNameArr.includes(orderName)) {
            orderNameArr.push(orderName);
        }
        if(!zipCodeArr.includes(zipCode)) {
            zipCodeArr.push(zipCode);
        }
        if(dict[parsedData[i]["orderName"]] > 0) {
            dict[parsedData[i]["orderName"]]++;
        } else {
            dict[parsedData[i]["orderName"]] = 1;
        }
     }

     console.log(dict);

     //Create header array for google api
     var headingArray = new Array(orderNameArr.length+1);
     headingArray[0] = "Zip Code";
     var j=0;

     for(var i=1; i<=orderNameArr.length; i++) {
        headingArray[i] = orderNameArr[j]; 
        j++;
     }

     data[0] = headingArray;
     var m =1;
    
     //Loop through jsondata and create an array of arrays to plot the chart;
    for(var i=0; i<zipCodeArr.length; i++) {
        var dataArr = new Array(headingArray.length);
        dataArr[0] = zipCodeArr[i];
        for(var j=0; j<orderNameArr.length; j++) {
            for(k=0; k<parsedData.length; k++) {
                if(parsedData[k]["orderName"] === zipCodeArr[i] && parsedData[k]["orderName"] === orderNameArr[j]) {                    
                    dataArr[j+1] = parseInt(dict[parsedData[k]["orderName"]]);                   
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
        
     }
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
        'width':600,
        'height':650,
          chart: {
            title: 'All Products Chart',
            subtitle: orderNames,
          },
          bars: 'horizontal' // Required for Material Bar Charts.
        };

    var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
    chart.draw(chartData, options);
}




