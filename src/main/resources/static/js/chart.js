$(() => {
	Highcharts.chart('mainChart', {
		title: {
			text: title
		},

		subtitle: {
			text: subtitle
		},

		yAxis: {
			title: {
				text: '%'
			},
			min: 0,
			max: 100
		},

		xAxis: {},

		legend: {
			layout: 'vertical',
			align: 'right',
			verticalAlign: 'middle'
		},

		series: [
			{
				name: 'Throttle',
				data: throttle,
				color: '#61eb34'
			},
			{
				name: 'Brake',
				data: brake,
				color: '#eb0f07'
			},
			{
				name: 'Clutch',
				data: clutch,
				color: '#05acfa'
			}
		],

		responsive: {
			rules: [{
				chartOptions: {
					legend: {
						layout: 'horizontal',
						align: 'center',
						verticalAlign: 'bottom'
					}
				}
			}]
		}

	});
});