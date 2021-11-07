package net.thexcoders.aladin_bot_backend.nlp_models.categorizer;

import org.springframework.ui.ModelMap;

import java.util.List;


public class CategoryResultImpl extends CategorizerImpl.CategoryResult {

    public CategoryResultImpl(String category, double probability, String input) {
        super(category, probability, input);
    }

    public boolean isEqual(CategorizerImpl.CategoryResult res) {
        return this.catCode == res.catCode;
    }

    public boolean isIn(List<CategorizerImpl.CategoryResult> list) {
        for (CategorizerImpl.CategoryResult res : list) {
            if (isEqual(res)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "CategoryResult{" +
                "category='" + category + '\'' +
                ", probability=" + probability +
                ", catCode=" + catCode +
                '}';
    }

    public ModelMap toModelMap() {
        ModelMap res = new ModelMap();
        res.addAttribute("probability", probability);
        res.addAttribute("catCode", catCode);
        res.addAttribute("category", category);
        return res;
    }


}
