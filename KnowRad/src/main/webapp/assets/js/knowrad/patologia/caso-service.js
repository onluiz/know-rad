"use strict"

var CasoService = {

    service: new AbstractService("caso/"),

    remove: function(idCaso, callback) {
        this.service.doGET("remove", ({"idCaso": idCaso}), callback);
    },

    /**
     * FINDS
     */

    findAllModalidadesDTOByidCaso: function(idCaso, callback) {
        this.service.doGET("findAllModalidadesDTOByidCaso", ({"idCaso": idCaso}), callback);
    },

    findAllPatologiasDTOByidCaso: function(idCaso, callback) {
        this.service.doGET("findAllPatologiasDTOByidCaso", ({"idCaso": idCaso}), callback);
    },

    /**
     * SAVES
     */

    save: function(casoDTO, callback) {
        this.service.doPOST("save", JSON.stringify(casoDTO), callback);
    },

    saveCasoModalidade: function(idCaso, idModalidade, callback) {

        this.service.doGET("saveCasoModalidade", ({idCaso: idCaso, idModalidade: idModalidade}), callback);

    },

    savePatologiaCaso: function(idCaso, idPatologia, callback) {

        this.service.doGET("savePatologiaCaso", ({idCaso: idCaso, idPatologia: idPatologia}), callback);

    }

};