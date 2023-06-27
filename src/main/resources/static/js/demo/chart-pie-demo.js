// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

// Pie Chart Example
var ctx = document.getElementById("myPieChart");
let Mercury = document.getElementById("mercuryCount").value;
let Mars = document.getElementById("marsCount").value;
let Earth = document.getElementById("earthCount").value;
let Jupiter = document.getElementById("jupiterCount").value;
let total = (Mercury + Mars + Earth + Jupiter)/100;

console.log(Mercury);
console.log(Mars);
console.log(Earth);
console.log(Jupiter);


var myPieChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: ["Mercury", "Mars", "Earth","Jupiter"],
    datasets: [{
      data: [Mercury, Mars, Earth, Jupiter],
      backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc','#f6c23e'],
      hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf','#dda20a'],
      hoverBorderColor: "rgba(234, 236, 244, 1)",
    }],
  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    cutoutPercentage: 80,
  },
});
