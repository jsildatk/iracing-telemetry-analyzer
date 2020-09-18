$(() => {
	const select = $('#lap');
	$(select).on('change', function () {
		const lap = laps[select.val()];
		const throttle = lap.data[44].value;
		const brake = lap.data[43].value;
		const clutch = lap.data[42].value;

		Highcharts.chart('mainChart', {
			title: {
				text: title + ' (' + lap.lapTime + ') '
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

			xAxis: {
				// title: {
				// 	text: 'Lap time'
				// },
				// min: 0,
				// max: lapTime
			},

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
});