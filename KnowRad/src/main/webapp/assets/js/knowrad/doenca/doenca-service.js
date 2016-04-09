"use strict"

var DoencaService = {

    service: new AbstractService("doenca/"),

    remove: function(id, callback) {
        this.service.doGET("remove", ({"id": id}), callback);
    },

    /**
     * FINDS
     */

    findDTOById: function(id, callback) {
        this.service.doGET("findDTOById", ({"id": id}), callback);
    },

    findDoencaResponseById: function(id, callback) {
        this.service.doGET("findDoencaResponseById", ({"id": id}), callback);
    },

    /**
     * SAVES AND UPDATES
     */

    save: function(dto, callback) {
        this.service.doPOST("save", JSON.stringify(dto), callback);
    },

    update: function(doencaDTO, callback) {
        this.service.doPOST("update", JSON.stringify(doencaDTO), callback);
    },

    saveTermo: function(dto, callback) {
        this.service.doPOST("saveTermo", JSON.stringify(dto), callback);
    },

    updateTermo: function(dto, callback) {
        this.service.doPOST("updateTermo", JSON.stringify(dto), callback);
    },

    removeTermo: function(id, callback) {
        this.service.doGET("removeTermo", ({"id": id}), callback);
    },

    findListDTOByIdDoenca: function(id, callback) {
        this.service.doGET("findListDTOByIdDoenca", ({"id": id}), callback);
    }

};