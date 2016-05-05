"use strict"

var PatologiaService = {

    service: new AbstractService("patologia/"),

    remove: function(id, callback) {
        this.service.doGET("remove", ({"id": id}), callback);
    },

    /**
     * FINDS
     */

    findDTOById: function(id, callback) {
        this.service.doGET("findDTOById", ({"id": id}), callback);
    },

    findPatologiaResponseById: function(id, callback) {
        this.service.doGET("findPatologiaResponseById", ({"id": id}), callback);
    },

    /**
     * SAVES AND UPDATES
     */

    save: function(dto, callback) {
        this.service.doPOST("save", JSON.stringify(dto), callback);
    },

    update: function(patologiaDTO, callback) {
        this.service.doPOST("update", JSON.stringify(patologiaDTO), callback);
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

    findListDTOByIdPatologia: function(id, callback) {
        this.service.doGET("findListDTOByIdPatologia", ({"id": id}), callback);
    }

};