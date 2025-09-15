package com.abm.apps.evcommons.beans.domain.http.enumtypes;

/**
 * Specifies the type of the meta-information ( main description of the Information, a relation to another record, an additional TAG and maybe more... )
 * <p>
 * (1)
 * MAIN_INFORMATION must be unique, it is the main Identifier, and can also hold a Value
 * It is used for the cases where you need to store a Value, or it is used as a main Identifier for a Relation, Tag
 * <p>
 * (2) VALUE_INFORMATION can be used only for lists of metaInformations which are under a MAIN_INFORMATION
 * It is used to store Values either Text or Binaries
 * <p>
 * (3) RELATION can be used only for lists of metaInformations which are under a MAIN_INFORMATION
 * It is used to describe a Relation to another MAIN_INFORMATION, in the contexts of aggregation
 * <p>
 * (4) TAG can be used only for lists of metaInformations which are under a MAIN_INFORMATION
 * It is used to tag a MAIN_INFORMATION with different information which will be used during queries, searches or to simply display additional informations (meta-informations) for a MAIN_INFORMATION
 */
public enum InformationType {
    WRAPPER, VALUE_STORED, RELATION, TAG;
}
