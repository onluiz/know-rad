"use strict"

var CasoService = {

    service: new AbstractService("caso/"),

    save: function(casoSaveResponseDTO, callback) {
        this.service.doPOST("save", JSON.stringify(casoSaveResponseDTO), callback);
    },

    remove: function(idCaso, callback) {
        this.service.doGET("remove", ({"idCaso": idCaso}), callback);
    },

    findAllByidCaso: function(idCaso, callback) {
        this.service.doGET("findAllByidCaso", ({"idCaso": idCaso}), callback);
    }

};