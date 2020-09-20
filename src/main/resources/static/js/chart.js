$(() => {
	function getDataByType(lap, type) {
		return lap.data.find(singleData => singleData.type === type);
	}

	const select = $('#lap');
	$(select).on('change', function () {
		const lap = laps[select.val()];
		const throttle = getDataByType(lap, "Throttle").value;
		const brake = getDataByType(lap, "Brake").value;
		const clutch = getDataByType(lap, "Clutch").value;
		const rpm = getDataByType(lap, "RPM").value;
		const gear = getDataByType(lap, "Gear").value;
		const angle = getDataByType(lap, "SteeringWheelAngle").value;

		Highcharts.chart('mainChart', {
			title: {
				text: title + ' (' + lap.lapTime + ') '
			},

			subtitle: {
				text: subtitle
			},

			yAxis: [
				{
					title: {
						text: 'Round'
					},
					min: lap.minRpm,
					max: lap.maxRpm,
					height: '20%',
					lineWidth: 2,
					startOnTick: false,
					endOnTick: false
				},
				{
					title: {
						text: 'Degree (positive = left, negative = right)'
					},
					min: lap.minSteeringAngle,
					max: lap.maxSteeringAngle,
					top: '25%',
					height: '20%',
					offset: 0,
					lineWidth: 2,
					startOnTick: false,
					endOnTick: false
				},
				{
					title: {
						text: 'Number'
					},
					min: lap.minGear,
					max: lap.maxGear,
					top: '50%',
					height: '20%',
					offset: 0,
					lineWidth: 2,
					startOnTick: false,
					endOnTick: false
				},
				{
					title: {
						text: '%'
					},
					min: 0,
					max: 100,
					top: '75%',
					height: '25%',
					offset: 0,
					lineWidth: 2
				}
			],

			xAxis: {},

			legend: {
				layout: 'vertical',
				align: 'right',
				verticalAlign: 'middle'
			},

			series: [
				{
					name: 'RPM',
					data: rpm,
					color: '#ed5102'
				},
				{
					name: 'Steering angle',
					data: angle,
					color: '#c21737',
					yAxis: 1
				},
				{
					name: 'Gear',
					data: gear,
					color: '#bfde10',
					yAxis: 2
				},
				{
					name: 'Throttle',
					data: throttle,
					color: '#61eb34',
					yAxis: 3
				},
				{
					name: 'Brake',
					data: brake,
					color: '#eb0f07',
					yAxis: 3
				},
				{
					name: 'Clutch',
					data: clutch,
					color: '#05acfa',
					yAxis: 3
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