"use strict";

var AbstractService = function(serviceUrl) {

    this.serviceUrl = serviceUrl;

};

AbstractService.prototype.doGET = function(to, data, callback) {

    $.ajax({
        type : "GET",
        contentType : "application/json; charset=utf-8",
        url: URL_BASE + this.serviceUrl + to,
        cache: false,
        data: data,
        success: callback.done,
        error: callback.fail
    });

};

AbstractService.prototype.doPOST = function(to, data, callback) {

    $.ajax({
        type : "POST",
        contentType : "application/json; charset=utf-8",
        url: URL_BASE + this.serviceUrl + to,
        cache: false,
        data: data,
        success: callback.done,
        error: callback.fail
    });

};

AbstractService.prototype.doFormPOST = function(to, formData, callback) {

    $.ajax({
        type:'POST',
        url: URL_BASE + this.serviceUrl + to,
        data: formData,
        cache:false,
        contentType: false,
        processData: false,
        success: callback.done,
        error: callback.fail
    });

};