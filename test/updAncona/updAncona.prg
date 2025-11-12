
    use kit_vari index kvari_id	alias "KITV" shared new

    use tipokit index tikit_id	alias "TKIT" shared new
    
    use kit index kit_bc	alias "KIT" shared new
    
    use ancona 			alias "ANC" new
    
    ANC->(dbgotop())
    while(ANC->(!eof()))
	
	if KIT->(dbseek( ANC->ID_KIT) )
	    if KIT->IDtipoKit > 0
		tNome := getTkit(KIT->IDtipoKit)
		@ 04, 01 say "TipoKit: ID=" + ltrim(str(KIT->IDtipoKit))
		
	    elseif KIT->categoria > 0
		tNome := getKitv(KIT->categoria)
		@ 04, 01 say "KitVar: Categoria=" + ltrim(str(KIT->categoria))
		
	    else
		tNome := ""
		@ 05, 01 say "Errore:  TipoKit=	" + ltrim(str(KIT->IDtipoKit)) + "  Categoria=" + ltrim(str(KIT->categoria))
	    	
	    endif
	
	    ANC->DESC_KIT := tNome
	endif
	
	ANC->(dbskip())
    enddo
    
    ANC->(dbclosearea())
    
    KIT->(dbclosearea())
    
    TKIT->(dbclosearea())
    
    KITV->(dbclosearea())
    
quit

*-------------------------------
Function getTkit(pID)
*-------------------------------
LOCAL ret := ""
    if TKIT->(dbseek(pID))
	ret := TKIT->Nome
    endif
return(ret)
*-------------------------------
Function getKitv(pID)
*-------------------------------
LOCAL ret := ""
    if KITV->(dbseek(pID))
	ret := KITV->Nome
    endif
return(ret)
