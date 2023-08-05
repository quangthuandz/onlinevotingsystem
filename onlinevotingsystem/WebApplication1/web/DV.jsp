<%-- 
    Document   : DV
    Created on : May 24, 2023, 11:35:49 AM
    Author     : khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <canvas id="myChart" style="width: 500px; height: 500px"></canvas>
        
        <canvas id="currentResultChart" style="width: 500px; height: 500px"></canvas>

        <script>
            // JavaScript code to create and configure the chart
            var ctx = document.getElementById('myChart').getContext('2d');
            var myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ['Label 1', 'Label 2', 'Label 3', 'Label 4'],
                    datasets: [{
                            label: 'ok',
                            data: [10, 20, 30, 100]
                        }]
                }
            });
        </script>
        <script>
            var ctx = document.getElementById("currentResultChart");
            var currentResultChart = new Chart(ctx, {
                type: 'doughnut',
                data: {
                    labels: ["Direct", "Referral", "Social"],
                    datasets: [{
                            data: [55, 30, 15],
                            backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc'],
                            hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],
                            hoverBorderColor: "rgba(234, 236, 244, 1)"
                        }]
                }
            });
        </script>
    </body>
</html>
