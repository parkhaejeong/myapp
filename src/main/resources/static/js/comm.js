/**
 *  @description CommonClass
 */
// $(document).ready(function () {});
// jQuery(function($){});
$(function ($) {
    "use strict";

    $.extend({
        // queryString을 키값쌍인 hash로 변환합니다.
        getUrlVars: function () {
            let vars = {}, hash;
            if (window.location.href.indexOf('=') < 0) {
                return vars;
            }
            let queryString = window.location.href.slice(window.location.href.indexOf('?') + 1);
            if (COM.setup().useClientEncrypt) {
                let key = CryptoJS.enc.Utf8.parse(aesValue.key);
                let iv = CryptoJS.enc.Utf8.parse(aesValue.iv);
                if (queryString.toLowerCase().indexOf('encryptedrequest=') >= 0) {
                    queryString = CryptoJS.AES.decrypt(queryString.slice(queryString.indexOf('=') + 1), key, {
                        iv: iv,
                        padding: CryptoJS.pad.Pkcs7
                    });
                }
            }
            let hashes = queryString.split('&');
            for (let i = 0; i < hashes.length; i++) {
                hash = hashes[i].split('=');
                if (COM.isNullOrEmpty(hash[0])) {
                    continue;
                }
                hash[0] = $.trim(hash[0]);
                vars[hash[0]] = decodeURIComponent(hash[1]);
            }
            return vars;
        },
        // queryString에서 키값으로 값을 찾습니다.
        getUrlVar: function (name) {
            let obj = $.getUrlVars();
            let v = null;
            $.each(obj, function (key, value) {
                if ($.trim(name.toLowerCase()) === $.trim(key.toLowerCase())) {
                    v = value;
                }
            });
            return v;
        },
        // checkbox data to string
        getValueComma: function (selectEl) {
            let arr = [];
            let str, val;
            if (!COM.isNullOrEmpty(selectEl)) {
                selectEl.each(function(idx, el){
                    val = $(el).val();
                    arr.push(val);
                });
                str = arr.join(',');
            }
            return str;
        }
    });

    $.extend($.fn, {
        // 주어진 요소 내부 element들의 값들을 object로 만들어서 반환
        serializeObject: function () {
            var result = {};
            var extend = function (i, element) {
                var node = result[element.name];
                if ('undefined' !== typeof node && node !== null) {
                    if ($.isArray(node)) {
                        node.push(element.value);
                    } else {
                        result[element.name] = [node, element.value];
                    }
                } else {
                    result[element.name] = element.value;
                }
            };

            $.each(this.serializeArray(), extend);
            return result;
        }
    });
}(jQuery));

function CommonClass() {
    let defaultSetup = {
        mode: 'Release',            // Release or Debug
        errorViewType: 'tooltip',  // 'tooltip' 혹은 'alert'
        modalType: 'layer',          // 'layer' 혹은  'alert'
        tooltipTimer: 2000,
        useClientEncrypt: false,  //클라이언트 에서 서버로 보낼때 암호화를 사용 할 것인가?
        defaultTooltipPosition: 'bottom',
        useServerEncrypt: false,     // 서버에서 클라이언트로 받을때 암호화를 사용 할 것인가?
        useDefaultModalTitle: false //modal에 title이 없다면..자동생성할 것인가
    };

    this.getContainer = function(selector){
        if( typeof jQuery == 'function' && typeof this.container !== 'object' ){
            this.container = $(selector);
        }
        return this.container;
    };

    this.isNull = function (obj) {
        return typeof obj === 'undefined' || obj === null || obj === NaN;
    };

    // 사용법 : COM.isNullOrEmpty(obj)
    this.isNullOrEmpty = function (obj) {
        return typeof obj === 'undefined' || obj === null || obj === 'undefined' || obj === '';
    };

    this.setup = function (opt) {
        if (this.isNull(opt)) {
            return defaultSetup;
        } else {
            return $.extend(defaultSetup, opt);
        }
    };

    this.callAjax = function (url, type, dataType, data, callbackSuccess, callbackFailure, callbackComplete) {
        //var deferred = Q.defer();
        $.ajax({
            // headers: {"Content-Type": "text/html;charset=UTF-8", "X-HTTP-Method-Override": "POST"},
            url: url,
            type: type,
            data: data,
            dataType: dataType,
            // data: JSON.stringify(data),
            // contentType: 'application/json',
            cache: false,
            success: function (result, textStatus, data) {
                callbackSuccess(result, textStatus, data);
            },
            error: function (xhr, errorName, error) {
                if (!COM.isNullOrEmpty(callbackFailure)) {
                    callbackFailure(xhr, errorName, error);
                }
            },
            complete : function()
            {
                if (!COM.isNullOrEmpty(callbackComplete)) {
                    callbackComplete();
                }
            }
        });
        //return deferred.promise;
    };

    /**
     * front form validation check
     * 사용법 : COM.validate(form 셀렉터)
     * @param validate
     * @returns {string}
     */
    this.validate = function (validate) {
        var bool = '';
        Array.prototype.slice.call(validate).forEach(function (form) {
            if (!form.checkValidity()) {
                form.classList.add('was-validated');
                bool = false;
            }else {
                bool = true;
            }
        })
        return bool;
    }

    /**
     * 전화번호 정규식
     * @param value
     * @returns {*}
     */
    this.validationTel = function (value) {
        return value.replace(/[^0-9]/g, '').replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
    }

    /**
     * modal open
     * @param popupId
     * @param container
     * @param openCallback
     * @returns {*}
     */
    this.openPopup = function (popupId, container, openCallback) {
        let $container = container;
        return $.get('/popup/' + popupId, function (response) {
            let $pop = $(response);

            if (!COM.isNull(openCallback)) {
                openCallback($pop);
            }

            $container.addClass("pop_container");
            $container.append($pop);
            $pop.fadeIn(200);

        });
    };

    /**
     * 특수문자제거
     * @param inText
     * @returns {*}
     */
    this.symbolRemove=function(inText){
        let regex = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi
        return inText.replace(regex, "");
    };

    /**
     * 숫자만 반환
     * @param inText
     * @returns {*}
     */
    this.numberOnly=function(inText){
        let regex = /[^0-9]/g;
        return inText.replace(regex, "");
    };

    /**
     * int 숫자 반환
     * @param inText
     * @returns {number}
     */
    this.intOnly=function(inText){
        let num = COM.numberOnly(inText);
        return num === '' ? 0 : parseInt(num);
    }

    /**
     * 숫자 콤마
     * @param inText
     * @returns {number}
     */
    this.numComma =function(inText){
        return inText.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    }

    /**
     * modal에서 부모창으로 데이터 바인딩
     */
    this.bindData = function (parent, data) {
        $.each(data, function (key, value) {
            if(parent.find('[name='+key+']').length > 0){
                parent.find('[name='+key+']').val(value).change();
            }
        });
    }

    /**
     * mode 체크
     * @param wnd
     * @param data
     * @returns {string}
     */
    this.modeCheck = function () {
        let fullUrl = window.location.href;
        let searchIdx = fullUrl.indexOf('?');
        let tmp = fullUrl.substring(fullUrl.lastIndexOf('/') + 1);
        return searchIdx > 0 ? 'modify' : tmp.substring(tmp.lastIndexOf('_') + 1);
    }

    this.showLoading = function () {
        $('body').find('#Loading').show();
    }

    this.hideLoading = function () {
        $('body').find('#Loading').hide();
    }
}

let COM = new CommonClass();