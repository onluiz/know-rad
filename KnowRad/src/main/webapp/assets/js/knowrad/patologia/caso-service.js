"use strict"

var CasoService = {

    service: new AbstractService("caso/"),

    /**
     * REMOVES
     */
    remove: function(idCaso, callback) {
        this.service.doGET("remove", ({"idCaso": idCaso}), callback);
    },

    removeCasoModalidade: function(idCaso, idModalidade, callback) {
        this.service.doGET("removeCasoModalidade", ({idCaso: idCaso, idModalidade: idModalidade}), callback);
    },

    /**
     * FINDS
     */

    findDTOById: function(idCaso, callback) {
        this.service.doGET("findDTOById", ({"idCaso": idCaso}), callback);
    },

    findAllModalidadesDTOByidCaso: function(idCaso, callback) {
        this.service.doGET("findAllModalidadesDTOByidCaso", ({"idCaso": idCaso}), callback);
    },

    findAllPatologiasDTOByidCaso: function(idCaso, callback) {
        this.service.doGET("findAllPatologiasDTOByidCaso", ({"idCaso": idCaso}), callback);
    },

    /**
     * SAVES AND UPDATES
     */

    save: function(casoDTO, callback) {
        this.service.doPOST("save", JSON.stringify(casoDTO), callback);
    },

    update: function(casoDTO, callback) {
        this.service.doPOST("update", JSON.stringify(casoDTO), callback);
    },

    saveCasoModalidade: function(idCaso, idModalidade, callback) {

        this.service.doGET("saveCasoModalidade", ({idCaso: idCaso, idModalidade: idModalidade}), callback);

    },

    savePatologiaCaso: function(idCaso, idPatologia, callback) {

        this.service.doGET("savePatologiaCaso", ({idCaso: idCaso, idPatologia: idPatologia}), callback);

    }

};