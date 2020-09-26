$(() => {

	function getDataByType(lap, type) {
		return lap.data.find(singleData => singleData.type === type);
	}

	function checkColumn(columns, toCheck) {
		return columns.find(column => column === toCheck);
	}

	function getSelectedColumns() {
		let selectedColumns = [];
		$('#columns input:checked').each(function () {
			selectedColumns.push($(this).attr('id'));
		});
		return selectedColumns;
	}

	function resolveMinAndMax(lap, type) {
		switch ( type ) {
			case 'RPM':
				return [lap.minRpm, lap.maxRpm];
			case 'SteeringWheelAngle':
				return [lap.minSteeringAngle, lap.maxSteeringAngle];
			case 'Gear':
				return [lap.minGear, lap.maxGear];
			case 'Speed':
				return [lap.minSpeed, lap.maxSpeed];
			case 'dcMGUKDeployMode':
				return [25, 75];
			case 'DRS_Status':
				return [0, 3];
			default :
				return null;
		}
	}

	/********************************************************************************/

	const select = $('#lap');
	$(select).on('change', function () {
		const columns = getSelectedColumns();
		if ( columns.length === 0 ) {
			alert('You must select at least 1 column');
			return;
		}

		const lap = laps[select.val()];
		let toDraw = [];
		let index = 0;
		for ( let i = 0; i < columns.length; i++ ) {
			if ( columns[i] !== 'Inputs' ) {
				toDraw[index] = getDataByType(lap, columns[i]);
				index++;
			}
		}
		let yAxisLength = index;
		if ( checkColumn(columns, 'Inputs') ) {
			yAxisLength++;
			toDraw[index] = getDataByType(lap, 'Throttle');
			toDraw[index + 1] = getDataByType(lap, 'Brake');
			toDraw[index + 2] = getDataByType(lap, 'Clutch');
		}

		let series = [];
		for ( let i = 0; i < toDraw.length; i++ ) {
			series.push(setSeriesData(toDraw[i], i));
		}

		let yAxis = [];
		const height = (100 / yAxisLength) - 1.5;
		let top = 0;
		for ( let i = 0; i < yAxisLength; i++ ) {
			if ( toDraw[i].type === 'Throttle' ) {
				yAxis.push(setYAxisInputsData(top, height));
				break;
			} else {
				const minAndMax = resolveMinAndMax(lap, toDraw[i].type);
				yAxis.push(setYAxisData(toDraw[i], top, height, minAndMax[0], minAndMax[1]));
			}
			top += height + 2;
		}

		Highcharts.chart('mainChart', {
			title: {
				text: title
			},

			subtitle: {
				text: subtitle
			},

			yAxis: yAxis,
			xAxis: {},

			legend: {
				layout: 'vertical',
				align: 'right',
				verticalAlign: 'middle'
			},

			series: series,

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