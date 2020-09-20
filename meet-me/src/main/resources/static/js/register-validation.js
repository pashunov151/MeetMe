"use strict";
(function () {
    const fieldHandlerMap = {
        username: (tr) => usernameHandler(tr),
        password: (tr) => passwordHandler(tr),
        confirmPassword: (tr) => cPasswordHandler(tr),
        email: (tr) => emailHandler(tr),
        gender: (tr) => genderHandler(tr),
        interestedIn: (tr) => interestedInHandler(tr),
        born: (tr) => bornHandler(tr),
        location: (tr) => locationHandler(tr),
        firstName: (tr) => firstNameHandler(tr),
        lastName: (tr) => lastNameHandler(tr),
        skinColor: (tr) => skinColorHandler(tr),
        hairColor: (tr) => hairColorHandler(tr),
        eyeColor: (tr) => eyeColorHandler(tr),
        height: (tr) => heightHandler(tr),
        weight: (tr) => weightHandler(tr),
        picture: (tr) => pictureHandler(tr)

    };
    let validField = {
        username: false,
        password: false,
        confirmPassword: false,
        email: false,
        gender: false,
        interestedIn: false,
        born: false,
        location: false
    };
    let secondPageValidField = {
        firstName: false,
        lastName: false,
        skinColor: false,
        hairColor: false,
        eyeColor: false,
        height: false,
        weight: false,
        picture: false
    };
    let firstPageCommonFunction = commonFieldValidation.bind(undefined, 'next', validField);
    let secondPageCommonFunction = commonFieldValidation.bind(undefined, 'regForm', secondPageValidField);

    window.addEventListener("load", function () {
//TODO REMOVE FOR DEVELOPMENT PURPOSES ONLY
        document.getElementById("next").disabled = true;
        document.getElementById("regForm").disabled = true;
        document.getElementById('eTaken').style.display = 'none';
        document.getElementById("confirmPassword").disabled = true;

        document.addEventListener("click", function (e) {
            let tr = e.target.id;
            if (!fieldHandlerMap.hasOwnProperty(tr)) {
                return;
            }
            fieldHandlerMap[tr](e.target);
        });
    });

    function usernameHandler(tr) {
        tr.addEventListener("input", function (e) {
            firstPageCommonFunction('username', 'uNIndicator', e.target.value.length < 3);
        });
    }

    function passwordHandler(tr) {
        let el = document.getElementById("confirmPassword");
        if (document.getElementById("password").value.length < 3) {
            el.disabled = true;
            el.value = "";
        }
        tr.addEventListener("input", function (e) {
            firstPageCommonFunction('password', 'pNIndicator', e.target.value.length < 3);
            if (document.getElementById("password").value.length < 3) {
                el.disabled = true;
                el.value = "";
            } else {
                el.disabled = false;
            }
        });
    }

    function cPasswordHandler(tr) {
        tr.addEventListener("input", function (e) {
            let pass = e.target.value;
            firstPageCommonFunction('confirmPassword', 'cPNIndicator', document.getElementById("password").value !== pass);
        });
    }

    async function emailHandler(tr) {
        document.getElementById('email').addEventListener("input", (e) => {
            let email = e.target.value;
            let a = `http://localhost:8080/db/users/${email}`;
            if (email.length > 0 && email.match(/\w+@{1}\w+\.\w{2,}/g)) {
                fetch(a, {mode: 'cors', method: 'GET'})
                    .then(r => {
                        let eTaken = document.getElementById('eTaken');
                        if (r.status === 204) {
                            eTaken.style.display = 'none';
                            return;
                        }
                        eTaken.style.display = '';
                    })
                    .catch(error => {
                        console.log('error is', error);
                    });

                firstPageCommonFunction("email", "eIndicator", !email.match(/\w+@{1}\w+\.\w+/g));
            }
        });
    }

    function genderHandler(tr) {
        firstPageCommonFunction('gender', 'sIndicator', tr.value === 'Sex');
    }

    function interestedInHandler(tr) {
        firstPageCommonFunction('interestedIn', 'lFIndicator', tr.value === 'Interested in');
    }

    //TODO fix age validation
    function bornHandler(tr) {
        tr.addEventListener("change", function (e) {
            let ageDifMs = Date.now() - new Date(tr.value).getTime();
            let ageDate = new Date(ageDifMs);
            let current = Math.abs(ageDate.getUTCFullYear() - 1970);


            firstPageCommonFunction('born', 'bIndicator', current < 18)
        });
    }

    function locationHandler(tr) {
        tr.addEventListener("input", function (e) {
            let current = e.target.value;
            firstPageCommonFunction('location', 'loIndicator', current.length < 2)
        });
    }

    function firstNameHandler(tr) {
        tr.addEventListener("input", function (e) {
            secondPageCommonFunction('firstName', 'fNIndicator', e.target.value.length < 2);
        });
    }

    function lastNameHandler(tr) {
        tr.addEventListener("input", function (e) {
            secondPageCommonFunction('lastName', 'lNIndicator', e.target.value.length === 0);
        });
    }

    function skinColorHandler(tr) {
        tr.addEventListener("input", function (e) {
            let input = e.target.value;
            secondPageCommonFunction('skinColor', 'sCNIndicator', input.length < 3)
        })
    }

    function hairColorHandler(tr) {
        tr.addEventListener("input", function (e) {
            let input = e.target.value;
            secondPageCommonFunction('hairColor', 'hCNIndicator', input.length < 3)
        })
    }

    function eyeColorHandler(tr) {
        tr.addEventListener("input", function (e) {
            let input = e.target.value;
            secondPageCommonFunction('eyeColor', 'eCNIndicator', input.length < 3)
        })
    }

    function pictureHandler(tr) {
        tr.addEventListener("change", function (e) {
            secondPageCommonFunction('picture', 'picIndicator', tr.value === undefined);
            let label = document.getElementById('picLabel');
            label.style.overflow = 'hidden';
            // label.textContent = tr.value.split(/(\\|\/)/g).pop();
        })
    }

    function heightHandler(tr) {
        tr.addEventListener("input", function (e) {
            let input = e.target.value;
            secondPageCommonFunction('height', 'hNIndicator', isNaN(input)/*input.length < 3*/)
        })
    }

    function weightHandler(tr) {
        tr.addEventListener("input", function (e) {
            let input = e.target.value;
            secondPageCommonFunction('weight', 'wNIndicator', isNaN(input)/*input.length < 3*/)
        })
    }

    function commonFieldValidation(pageWithWords, pageField, field, indicator, expression) {
        if (expression) {
            pageField[field] = false;
            document.getElementById(indicator).style.display = '';
        } else {
            pageField[field] = true;
            document.getElementById(indicator).style.display = 'none';
        }
        //TODO REMOVE FOR DEVELOPMENT PURPOSES ONLY
        document.getElementById(pageWithWords).disabled = buttonHandler(pageField) !== 0;
    }

    function buttonHandler(validField) {
        let valid = Object.entries(validField).find(e => e[1] === false);
        return valid !== undefined ? 1 : 0;
    }
}());