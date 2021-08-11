$('#registrationForm').validate({
	rules: {
		username: {
			required: true,
			noSpecialChar: true,
			minlength: 3,
			maxlength: 15,
		},
		fullName: {
			required: true,
			maxlength: 50,
		},
		email: {
			required: true,
			validEmail: true,
			maxlength: 320,
		},
		phoneNumber: {
			required: true,
			digits: true,
			maxlength: 10,
			vietnamPhone: true,
		},
		password: {
			required: true,
			minlength: 6,
		},
		confirmPassword: {
			required: true,
			equalTo: '#password',
		},
	},
	messages: {
		username: {
			required: 'Hãy nhập tên đăng nhập',
			noSpecialChar: 'Tên đăng nhập không được chứa ký tự đặc biệt',
			minlength: 'Tên đăng nhập tối thiểu 3 ký tự',
			maxlength: 'Tên đăng nhập tối đa 15 ký tự',
		},
		fullName: {
			required: 'Hãy nhập họ tên',
			maxlength: 'Họ tên tối đa 50 ký tự',
		},
		email: {
			required: 'Hãy nhập email',
			validEmail: 'Email không hợp lệ',
			maxlength: 'Email tối đa 320 ký tự',
		},
		phoneNumber: {
			required: 'Hãy nhập số điện thoại',
			digits: 'Vui lòng nhập số',
			maxlength: 'Số điện thoại tối đa 10 ký tự',
			vietnamPhone: 'Số điện thoại không hợp lệ'
		},
		password: {
			required: 'Hãy nhập mật khẩu',
			minlength: 'Mật khẩu tối thiểu 6 ký tự',
		},
		confirmPassword: {
			required: 'Hãy nhập xác nhận mật khẩu',
			equalTo: 'Mật khẩu xác nhận không khớp',
		},
	},
	errorElement: 'em',
	errorPlacement: function (error, element) {
		// Add the `help-block` class to the error element
		error.addClass('help-block');
		// console.log(error.attr('id'))
		if (element.prop('type') === 'checkbox') {
			error.insertAfter(element.parent('label'));
		} else {
			if(document.querySelector(`#${error.attr('id')}`) == null) {
				error.insertAfter(element);
			}
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

$.validator.addMethod("vietnamPhone", function (value, element) {
	return this.optional(element) || /(84|0[3|5|7|8|9])+([0-9]{8})\b/.test(value);
}, "Số điện thoại không hợp lệ");

$.validator.addMethod("noSpecialChar", function(value, element) {
	return this.optional(element) || /^\S*$/.test(value);
}, "Tên đăng nhập không được chứa ký tự đặc biệt");

$.validator.addMethod("validEmail", function(value, element) {
	return this.optional(element) || /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(value);
}, "Email không hợp lệ");
