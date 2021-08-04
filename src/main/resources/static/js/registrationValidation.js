$('#registrationForm').validate({
	rules: {
		username: {
			required: true,
			minlength: 3,
			maxlength: 15,
		},
		fullname: {
			required: true,
			maxlength: 50,
		},
		email: {
			required: true,
			email: true,
			maxlength: 320,
		},
		phoneNumber: {
			required: true,
			digits: true,
			maxlength: 10,
		},
		password: {
			required: true,
			minlength: 6,
		},
		confirm_password: {
			required: true,
			equalTo: '#password',
		},
	},
	messages: {
		username: {
			required: 'Hãy nhập tên đăng nhập',
			minlength: 'Tên đăng nhập tối thiểu 3 ký tự',
			maxlength: 'Tên đăng nhập tối đa 15 ký tự',
		},
		fullname: {
			required: 'Hãy nhập họ tên',
			maxlength: 'Họ tên tối đa 50 ký tự',
		},
		email: {
			required: 'Hãy nhập email',
			email: 'Email không hợp lệ',
			maxlength: 'Email tối đa 320 ký tự',
		},
		phoneNumber: {
			required: 'Hãy nhập số điện thoại',
			digits: 'Vui lòng nhập số',
			maxlength: 'Số điện thoại tối đa 10 ký tự',
		},
		password: {
			required: 'Hãy nhập mật khẩu',
			minlength: 'Mật khẩu tối thiểu 6 ký tự',
		},
		confirm_password: {
			required: 'Hãy nhập xác nhận mật khẩu',
			equalTo: 'Mật khẩu xác nhận không khớp',
		},
	},
	errorElement: 'em',
	errorPlacement: function (error, element) {
		// Add the `help-block` class to the error element
		error.addClass('help-block');

		if (element.prop('type') === 'checkbox') {
			error.insertAfter(element.parent('label'));
		} else {
			error.insertAfter(element);
		}
	},
	highlight: function (element, errorClass, validClass) {
		$(element)
			.parents('.col-sm-5')
			.addClass('has-error')
			.removeClass('has-success');
	},
	unhighlight: function (element, errorClass, validClass) {
		$(element)
			.parents('.col-sm-5')
			.addClass('has-success')
			.removeClass('has-error');
	},
});
