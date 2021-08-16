// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
    'use strict';

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation');

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms).forEach(function (form) {
        form.addEventListener(
            'submit',
            function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }

                form.classList.add('was-validated');
            },
            false
        );
    });
})();

function debounce(func, timeout = 300){
    let timer;
    return (...args) => {
        clearTimeout(timer);
        timer = setTimeout(() => { func.apply(this, args); }, timeout);
    };
}

const form = document.querySelector('#loginForm');
const inputs = document.querySelectorAll('input');
inputs.forEach((input) =>
    input.addEventListener(
        'input',
        debounce((e) => {
            if (!form.classList.contains('was-validated')) {
                if (e.target.value !== '') {
                    e.target.classList.remove('is-invalid');
                }
            }
        }, 500)
    )
);