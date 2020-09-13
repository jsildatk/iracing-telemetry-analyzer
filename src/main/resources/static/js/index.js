$(() => {
	$("#telemetry").change(function () {
		document.getElementById("filename").innerHTML =
			this.files[0].name;
	});
});

