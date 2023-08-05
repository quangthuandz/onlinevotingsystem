<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="override.css">
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="bootstrap/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrap/jquery.min.js" type="text/javascript"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <link href="/resources/css/styleRegister.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />



    </head>

    <style>
        .dashboard {
            display: flex;
            justify-content: space-between;
            background-color: #f1f1f1;
            padding: 20px;
        }

        .dashboard-item {
            display: flex;
            align-items: center;
            flex-basis: 30%;
            padding: 10px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .dashboard-item i {
            font-size: 24px;
            margin-right: 10px;
        }

        .dashboard-item.vote {
            background-color: #ff7675;
            color: #ffffff;
        }

        .dashboard-item.post {
            background-color: #74b9ff;
            color: #ffffff;
        }

        .dashboard-item.comment {
            background-color: #55efc4;
            color: #ffffff;
        }

        .dashboard-item .text-container {
            text-align: left;
        }

        .dashboard-item h3 {
            font-size: 18px;
            margin-bottom: 5px;
        }

        .dashboard-item p {
            font-size: 24px;
            font-weight: bold;
            margin-top: 0;
        }

        #countdown {
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: Arial, sans-serif;
            font-size: 24px;
            color: #333;
        }

        .countdown-item {
            text-align: center;
            margin: 0 10px;
        }

        .countdown-item span {
            display: block;
            font-size: 48px;
            font-weight: bold;
        }

        .countdown-item span:last-child {
            font-size: 18px;
            font-weight: normal;
            margin-top: 5px;
        }

        .chart-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            margin-top: 20px;
        }

        .chart-item-pie {
            width: 400px;
            margin: 10px;
        }
        .chart-item-column {
            width: 600px;
            margin: 10px;
        }

        h1{
            margin-top: 100px;
        }

        body {
            background-color: #f1f1f1;
        }

    </style>

    <body>
        <div>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top p-0">
                <a class="navbar-brand bg-primary px-4 py-3" href="home">
                    electriq
                </a>

                <button class="navbar-toggler px-4 py-3 border-0" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                        aria-label="Toggle navigation">
                    +
                </button>
            </nav>
        </div>

        <div class="row" style="margin-top:100px">
            <div class="col">
                <nav aria-label="breadcrumb" class=" rounded-3 p-3 mb-4">
                    <ol class="breadcrumb mb-0">
                        <li class="breadcrumb-item"><a href="userhome" style="text-decoration: none;">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Dashboard</li>
                    </ol>
                </nav>
            </div>
        </div>

        <div id="countdown">
            <div class="countdown-item">
                <span id="days">00</span>
                <span>Days</span>
            </div>
            <div class="countdown-item">
                <span id="hours">00</span>
                <span>Hours</span>
            </div>
            <div class="countdown-item">
                <span id="minutes">00</span>
                <span>Minutes</span>
            </div>
            <div class="countdown-item">
                <span id="seconds">00</span>
                <span>Seconds</span>
            </div>
        </div>

        <div class="dashboard">
            <div class="dashboard-item vote">
                <i class="fas fa-users"></i>
                <div class="text-container">
                    <h3>Number of voted ballot</h3>
                    <p>${votedballot}</p>
                    <h3>Total voters</h3>
                    <p>${voter}</p>
                </div>
            </div>
            <div class="dashboard-item post">
                <i class="fas fa-file-alt"></i>
                <div class="text-container">
                    <h3>Threads</h3>
                    <p>${threads}</p>
                </div>
            </div>
            <div class="dashboard-item comment">
                <i class="fas fa-comments"></i>
                <div class="text-container">
                    <h3>Comment</h3>
                    <p>${comments}</p>
                </div>
            </div>
        </div>

        <div class="chart-container" style="background-color: white;margin: 50px;border-radius: 10px">
            <div class="row">
                <div class="col-md-8 mb-4" style="margin-left: -100px">
                    <div class="chart-item-column">
                        <canvas id="barChart" width="1000" height="500"></canvas>
                    </div>
                </div>
                <div class="col-md-4" style="margin-left: 100px;margin-top: 50px">
                    <h2 id="average"></h2>
                    <h2 id="percentage"></h2>
                </div>
            </div>
        </div>

        <div class="chart-container" style="background-color: white;margin: 50px;border-radius: 10px">
            <div class="row">
                <div class="col-md-8 mb-4" style="margin-left: -100px">
                    <div class="chart-item-column">
                        <canvas id="barChartComment" width="1000" height="500"></canvas>
                    </div>
                </div>
                <div class="col-md-4" style="margin-left: 100px;margin-top: 50px">
                    <h2 id="averageComment"></h2>
                    <h2 id="percentageCm"></h2>
                </div>
            </div>
        </div>

        <div class="chart-container" style="background-color: white;margin: 50px;border-radius: 10px">
            <div class="row">
                <div class="col-md-8 mb-4" style="margin-left: -100px">
                    <div class="chart-item-column">
                        <canvas id="barChartVote" width="1000" height="500"></canvas>
                    </div>
                </div>
                <div class="col-md-4" style="margin-left: 100px;margin-top: 50px">
                    <h2 id="averageVote"></h2>
                    <h2 id="percentageVote"></h2>
                </div>
            </div>
        </div>

        <script>
            function countdown() {
                const endDate = new Date("${endTime}");
                const now = new Date();

                const timeDiff = endDate - now;

                if (timeDiff <= 0) {
                    document.getElementById("countdown").innerHTML = "Campaign has ended!";
                    return;
                }

                const days = Math.floor(timeDiff / (1000 * 60 * 60 * 24));
                const hours = Math.floor((timeDiff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                const minutes = Math.floor((timeDiff % (1000 * 60 * 60)) / (1000 * 60));
                const seconds = Math.floor((timeDiff % (1000 * 60)) / 1000);

                document.getElementById("days").textContent = padNumber(days);
                document.getElementById("hours").textContent = padNumber(hours);
                document.getElementById("minutes").textContent = padNumber(minutes);
                document.getElementById("seconds").textContent = padNumber(seconds);

                setTimeout(countdown, 1000);
            }

            function padNumber(number) {
                return number.toString().padStart(2, "0");
            }

            countdown();

            var data = ${data};

            var lastColumn = data[data.length - 1];
            var average = lastColumn / data.length;
            var roundedAverage = average.toFixed(3);

            document.getElementById("average").innerText = "Average Threads \n " + roundedAverage;

            var labels = [];
            for (var i = 1; i <= data.length; i++) {
                var label = "hour " + i;
                labels.push(label);
            }

// Tìm cột đầu tiên có giá trị khác 0
            var firstNonZeroIndex = 0;
            while (firstNonZeroIndex < data.length && data[firstNonZeroIndex] === 0) {
                firstNonZeroIndex++;
            }

// Kiểm tra nếu số lượng phần tử của data vượt quá 12, thì cắt bớt phần tử từ đầu mảng
            if (data.length > 12) {
                var start = Math.max(firstNonZeroIndex, data.length - 12);
                data = data.slice(start);
                labels = labels.slice(start);
            }

// Tính phần trăm tăng hoặc giảm của data trong mỗi giờ
            var percentageChange = 0;
            if (data.length >= 3) {
                var prevValue = data[data.length - 3];
                for (var i = data.length - 2; i < data.length; i++) {
                    var currentValue = data[i];
                    var change = (currentValue - prevValue) / prevValue * 100;
                    percentageChange = change.toFixed(2);
                    prevValue = currentValue;
                }
            }
            document.getElementById("percentage").innerText = "Threads Per Hour \n ↑ " + percentageChange + " %";

// Dữ liệu số liệu cột và hours
            var chartData = {
                labels: labels,
                datasets: [{
                        label: "Number of Threads",
                        data: data,
                        backgroundColor: "rgba(75, 192, 192, 0.2)",
                        borderColor: "rgba(75, 192, 192, 1)",
                        borderWidth: 1,
                        stack: "group1"
                    }]
            };

// Tùy chọn biểu đồ
            var options = {
                scales: {
                    x: {
                        beginAtZero: true,
                        stepSize: 1,
                        title: {
                            display: true,
                            text: 'Hours Past'
                        }
                    },
                    y: {
                        title: {
                            display: true,
                            text: 'Threads'
                        }
                    }
                }
            };

            var ctx = document.getElementById("barChart").getContext("2d");
            var myChart = new Chart(ctx, {
                type: "bar",
                data: chartData,
                options: options
            });

            var dataComment = ${dataComment};

            var lastColumnCm = dataComment[dataComment.length - 1];
            var averageCm = lastColumnCm / dataComment.length;
            var roundedAverageCm = averageCm.toFixed(3);

            document.getElementById("averageComment").innerText = "Average Comments \n" + roundedAverageCm;

            var labelsCm = [];
            for (var i = 1; i <= dataComment.length; i++) {
                var label = "hour " + i;
                labelsCm.push(label);
            }

            var firstNonZeroIndexCm = 0;
            while (firstNonZeroIndexCm < dataComment.length && dataComment[firstNonZeroIndexCm] === 0) {
                firstNonZeroIndexCm++;
            }

// Kiểm tra nếu số lượng phần tử của data vượt quá 12, thì cắt bớt phần tử từ đầu mảng
            if (dataComment.length > 12) {
                var start = Math.max(firstNonZeroIndexCm, dataComment.length - 12);
                dataComment = dataComment.slice(start);
                labelsCm = labelsCm.slice(start);
            }

// Tính phần trăm tăng hoặc giảm của data trong mỗi giờ
            var percentageChangeCm = 0;
            if (dataComment.length >= 3) {
                var prevValueCm = dataComment[dataComment.length - 3];
                for (var i = dataComment.length - 2; i < dataComment.length; i++) {
                    var currentValueCm = dataComment[i];
                    var changeCm = (currentValueCm - prevValueCm) / prevValueCm * 100;
                    percentageChangeCm = changeCm.toFixed(2);
                    prevValueCm = currentValueCm;
                }
            }
            document.getElementById("percentageCm").innerText = "Comments Per Hour \n ↑ " + percentageChangeCm + " %";
            var chartDataComment = {
                labels: labelsCm,
                datasets: [{
                        label: "Number of Comments",
                        data: dataComment,
                        backgroundColor: "rgba(75, 192, 192, 0.2)",
                        borderColor: "rgba(75, 192, 192, 1)",
                        borderWidth: 1,
                        stack: "group1"
                    }]
            };

            var optionsComment = {
                scales: {
                    x: {
                        beginAtZero: true,
                        stepSize: 1,
                        title: {
                            display: true,
                            text: 'Hours Past'
                        }
                    },
                    y: {
                        title: {
                            display: true,
                            text: 'Comments'
                        }
                    }
                }
            };

            var ctx = document.getElementById("barChartComment").getContext("2d");
            var myChart = new Chart(ctx, {
                type: "bar",
                data: chartDataComment,
                options: optionsComment
            });


            var dataVote = ${dataVote};

            var lastColumnVote = dataVote[dataVote.length - 1];
            var averageVote = lastColumnVote / dataVote.length;
            var roundedAverageVote = averageVote.toFixed(3);

            document.getElementById("averageVote").innerText = "Average Voters \n " + roundedAverageVote;

            var labelsVote = [];
            for (var i = 1; i <= dataVote.length; i++) {
                var label = "hour " + i;
                labelsVote.push(label);
            }

            var firstNonZeroIndexVote = 0;
            while (firstNonZeroIndexVote < dataVote.length && dataVote[firstNonZeroIndexVote] === 0) {
                firstNonZeroIndexVote++;
            }

// Kiểm tra nếu số lượng phần tử của data vượt quá 12, thì cắt bớt phần tử từ đầu mảng
            if (dataVote.length > 12) {
                var start = Math.max(firstNonZeroIndexVote, dataVote.length - 12);
                dataVote = dataVote.slice(start);
                labelsVote = labelsVote.slice(start);
            }

// Tính phần trăm tăng hoặc giảm của data trong mỗi giờ
            var percentageChangeVote = 0;
            if (dataVote.length >= 3) {
                var prevValueVote = dataVote[dataVote.length - 3];
                for (var i = dataVote.length - 2; i < dataVote.length; i++) {
                    var currentValueVote = dataVote[i];
                    var changeVote = (currentValueVote - prevValueVote) / prevValueVote * 100;
                    percentageChangeVote = changeVote.toFixed(2);
                    prevValueVote = currentValueVote;
                }
            }
            
            document.getElementById("percentageVote").innerText = "Voters Per Hour \n↑ " + percentageChangeVote + " %";
            var chartDataVote = {
                labels: labelsVote,
                datasets: [{
                        label: "Voted Ballot",
                        data: dataVote,
                        backgroundColor: "rgba(75, 192, 192, 0.2)",
                        borderColor: "rgba(75, 192, 192, 1)",
                        borderWidth: 1,
                        stack: "group1"
                    }]
            };

            var optionsVote = {
                scales: {
                    x: {
                        beginAtZero: true,
                        stepSize: 1,
                        title: {
                            display: true,
                            text: 'Hours Past'
                        }
                    },
                    y: {
                        title: {
                            display: true,
                            text: 'Voted Ballot'
                        }
                    }
                }
            };

            var ctx = document.getElementById("barChartVote").getContext("2d");
            var myChart = new Chart(ctx, {
                type: "bar",
                data: chartDataVote,
                options: optionsVote
            });

        </script>
    </body>
</html>
