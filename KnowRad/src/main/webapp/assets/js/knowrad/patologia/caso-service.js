"use strict"

var CasoService = {

    service: new AbstractService("caso/"),

    save: function(casoDTO, callback) {
        this.service.doPOST("save", JSON.stringify(casoDTO), callback);
    },

    remove: function(idCaso, callback) {
        this.service.doGET("remove", ({"idCaso": idCaso}), callback);
    },

    findAllByidCaso: function(idCaso, callback) {
        this.service.doGET("findAllByidCaso", ({"idCaso": idCaso}), callback);
    },

    saveCasoModalidade: function(idCaso, idModalidade, callback) {

        this.service.doGET("saveCasoModalidade", ({idCaso: idCaso, idModalidade: idModalidade}), callback);

    }

};