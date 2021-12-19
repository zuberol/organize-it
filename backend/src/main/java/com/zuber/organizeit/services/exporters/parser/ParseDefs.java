package com.zuber.organizeit.services.exporters.parser;

public class ParseDefs {

    void mergeFlashcard() {

    }


    public interface ParsableDef<E> { // E = Entity

    }

    // NoteCtx.(String metaTag) ->
    void hehe () {
        String lines =
        """
            F: hkasbdasbgdhkas?
                 P: 1000, L: hard
                 answer lalallallala
        """;
    }

    // String someLine -> metaTag: value, value, value :: record MetaTagPair(String, Array(String))
    //                                             :: record MetaTagPair(String, Array(String))

    // NoteCtx.Note.Flashcard :: set



    /*

        StructureParser {
            Parse
            parse(lines) {
                foreach line ->
            }

        }

        ParseCtx<Entity> {

            ParseCtx parentCtx()
            List<ParseCtx> childrenCtx()

            Entity getCtxObject()
            resolveMetaPairs andThen resolveProperAction andThen applyAction()
            abstract void mergePairWithEntity(MetaTagPair mp) {

                switch (mp.metaTag)
                    case P:
                    case L:
                    case Ref:
                    case
            }
        }

        record MetaTagPair(String metaTag, List<String> values)




    */




    // 1
    // check what context available?
    // get context
    //
    // 2
    // parse to MetaTagPair, getIndentLevel
    //      if indentLevel == ctxLevel + 1       ->    ctx.getCtxObject.merge(MetaTagPair)
    //      else ctx.resolveNestedCtx andThen ctx.addChildCtx andThen ctx.children.head.merge(MetaTagPair)




}
