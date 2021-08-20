// Example starter JavaScript for disabling form submissions if there are invalid fields
const func = (function () {
	'use strict';

	// Fetch all the forms we want to apply custom Bootstrap validation styles to
	var forms = document.querySelectorAll('.needs-validation');

	// Loop over them and prevent submission
	Array.prototype.slice.call(forms).forEach(function (form) {
		form.addEventListener(
			'submit',
			function (event) {
				const isValidFileSize = checkValidFileSize();
				if (!form.checkValidity() || !isValidFileSize) {
					event.preventDefault();
					event.stopPropagation();
				} else {
					let model = document.querySelector('#myModal');
					if (!model.classList.contains('show')) {
						event.preventDefault();
						$('#myModal').modal('show');
					}
				}

				form.classList.add('was-validated');
			},
			false
		);
	});
})();

$(document).ready(function () {
	$('button[name="commit"]').click(function () {
		$('#postForm').submit();
	});
});

function debounce(func, timeout = 250){
	let timer;
	return (...args) => {
		clearTimeout(timer);
		timer = setTimeout(() => { func.apply(this, args); }, timeout);
	};
}

const title = document.querySelector('#title');
const content = document.querySelector('#content');

function checkAllWhiteSpace(e) {
	if (e.target.value.trim() !== '') return;

	e.target.value = '';
}

const debounceCheckAllWhiteSpace = debounce((e) => checkAllWhiteSpace(e));

title.addEventListener('input', debounceCheckAllWhiteSpace);
content.addEventListener('input', debounceCheckAllWhiteSpace);

const MAX_IMG_SIZE = 2; //MB
const MAX_VIDEO_SIZE = 10; //MB
const MAX_IMG_SIZE_ERROR_MSG = `Dung lượng tối đa của file hình là ${MAX_IMG_SIZE}MB`;
const MAX_VIDEO_SIZE_ERROR_MSG = `Dung lượng tối đa của file video là ${MAX_VIDEO_SIZE}MB`;

function checkValidFileSize() {
	const fileInput = document.querySelector('#file-input');
	if (fileInput.value !== '') {
		const fileSize = fileInput.files[0].size / 1024 / 1024;
		const index = fileInput.value.lastIndexOf('.');
		const extension = fileInput.value.substring(index + 1).toLowerCase();
		if (extension === 'jpg' || extension === 'png') {
			if (fileSize > MAX_IMG_SIZE) {
				genderError('image');
				return false;
			}
		}

		if (extension === 'mpeg') {
			if (fileSize > MAX_VIDEO_SIZE) {
				genderError('video');
				return false;
			}
		}
	}
	return true;
}

function genderError(fileType) {
	const fileCaptionName = document.querySelector('.file-caption-name');
	fileCaptionName.classList.remove('is-valid');
	fileCaptionName.classList.add('is-invalid');
	let divFileInput = document.querySelector('.file-input');
	divFileInput.classList.add('has-error');
	const errorMsg = document.querySelector('.file-error-message');
	errorMsg.style.display = 'block';

	//create close button
	let closeButton = document.createElement('button');
	closeButton.classList.add('btn-close', 'kv-error-close', 'float-end');
	closeButton.setAttribute('aria-label', 'Close');
	closeButton.addEventListener('click', () => {
		divFileInput.querySelector('.btn-close').click();
	});

	if (errorMsg.childNodes.length > 0) {
		errorMsg.innerHTML = '';
	}

	//add close button to errorMSg
	errorMsg.appendChild(closeButton);

	//create errorMsg content
	let errorContent = document.createElement('span');
	switch (fileType) {
		case 'image':
			errorContent.innerText = MAX_IMG_SIZE_ERROR_MSG;
			errorMsg.appendChild(errorContent);
			break;
		case 'video':
			errorContent.innerText = MAX_VIDEO_SIZE_ERROR_MSG;
			errorMsg.appendChild(errorContent);
			break;
	}
}

//get reset button
const btnReset = document.querySelector('button[type="reset"]');

btnReset.addEventListener('click', () => {
	const form = document.querySelector('#postForm');
	form.classList.remove('was-validated');
});
