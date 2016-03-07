"use strict"

var CasoService = {

    service: new AbstractService("caso/"),

    save: function(casoSaveResponseDTO, callback) {

        this.service.doPOST("save", JSON.stringify(casoSaveResponseDTO), callback);

    },

};