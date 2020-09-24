const rpmDraw = { name: 'RPM', data: [], color: '#ed5102', yAxis: 0 };
const angleDraw = { name: 'SteeringWheelAngle', data: [], color: '#c21737', yAxis: 0 };
const gearDraw = { name: 'Gear', data: [], color: '#bfde10', yAxis: 0 };
const speedDraw = { name: 'Speed', data: [], color: '#112322', yAxis: 0 };
const throttleDraw = { name: 'Throttle', data: [], color: '#00ffa4', yAxis: 0 };
const brakeDraw = { name: 'Brake', data: [], color: '#eb0f07', yAxis: 0 };
const clutchDraw = { name: 'Clutch', data: [], color: '#05acfa', yAxis: 0 };
const drawCollection = [rpmDraw, angleDraw, gearDraw, speedDraw, throttleDraw, brakeDraw, clutchDraw];

const yAxisRpm = {
	label: 'RPM',
	title: {
		text: 'Rounds'
	},
	min: 0,
	max: 0,
	top: '',
	height: '',
	offset: 0,
	lineWidth: 2,
	startOnTick: false,
	endOnTick: false
};
const yAxisSteeringAngle = {
	label: 'SteeringWheelAngle',
	title: {
		text: 'Degree (positive = left, negative = right)'
	},
	min: 0,
	max: 0,
	top: '',
	height: '',
	offset: 0,
	lineWidth: 2,
	startOnTick: false,
	endOnTick: false
};
const yAxisGear = {
	label: 'Gear',
	title: {
		text: 'Number'
	},
	min: 0,
	max: 0,
	top: '',
	height: '',
	offset: 0,
	lineWidth: 2,
	startOnTick: false,
	endOnTick: false
};
const yAxisSpeed = {
	label: 'Speed',
	title: {
		text: 'Km/h'
	},
	min: 0,
	max: 0,
	top: '',
	height: '',
	offset: 0,
	lineWidth: 2,
	startOnTick: false,
	endOnTick: false
};
const yAxisInputs = {
	label: 'Inputs',
	title: {
		text: '%'
	},
	min: 0,
	max: 100,
	top: '',
	height: '',
	offset: 0,
	lineWidth: 2,
	startOnTick: false,
	endOnTick: false
};
const yAxisCollection = [yAxisRpm, yAxisSteeringAngle, yAxisGear, yAxisSpeed, yAxisInputs];

function setSeriesData(drawData, index) {
	let data = drawCollection.find(element => element.name === drawData.type);
	data.data = drawData.value;
	if ( data.name !== 'Brake' && data.name !== 'Clutch' ) {
		data.yAxis = index;
	} else if ( data.name === 'Brake' ) {
		data.yAxis = index - 1;
	} else if ( data.name === 'Clutch' ) {
		data.yAxis = index - 2;
	}
	return data;
}

function setYAxisData(yAxisData, top, height, min, max) {
	let data = yAxisCollection.find(element => element.label === yAxisData.type);
	data.height = height.toString() + '%';
	data.top = top.toString() + '%';
	data.min = min;
	data.max = max;
	return data;
}

function setYAxisInputsData(top, height) {
	let data = yAxisCollection[4]; // get all inputs
	data.height = height.toString() + '%';
	data.top = top.toString() + '%';
	return data;
}
