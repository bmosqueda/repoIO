const validator = function() {

  const errors_es = new Map([
    ['isNumber', 'es de tipo numérico'],
    ['required', 'es requerido'],
    ['maxValue', 'El valor máximo de '],
    ['minValue', 'El valor mínimo de '],
    ['minLength', 'La longitud mínima de '],
    ['maxLength', 'La longitud máximo de '],
    ['validEmail', 'debe ser un correo electronico valido'],
    ['regex', 'EL formato para ']
  ])

  const VALIDATIONS = {
    isNumber(value) {
      return !isNaN(value) && Number(value) !== 0 || undefined;
    },
    
    maxValue(value, top) {
      return value <= top;
    },
    
    minValue(value, top) {
      return value >= top;
    },

    minLength(str, length) {
      return str.length >= length;
    },

    maxLength(str, length) {
      return str.length <= length
    },
    
    required(value) {
      return value !== null && value !== undefined && value !== ''; 
    },

    validEmail(value) {
      return /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/.test(value);
    },

    regex(str, rgx) {
      return RegExp(rgx).text(str);
    }
  }

  validate = (object, rules) => {
    let errors = '';
    for (key in rules) {

      // gets all the rules for the field as a string
      let fieldRules = rules[key];

      // split the rules to use them individually
      fieldRules = fieldRules.split('|');

      for (let i = 0; i < fieldRules.length; i++) {
        /// rules that recibe arguments
        if (fieldRules[i].indexOf(',') !== -1) {
          fieldRules[i] = fieldRules[i].split(',');
          // checks if is an existing rule
          if (fieldRules[i][0] in VALIDATIONS) {
            if (!VALIDATIONS[fieldRules[i][0]](object[key], fieldRules[i][1]))
              errors += `${errors_es.get(fieldRules[i][0])}'${key}' es ${fieldRules [i][1]}.\n`;
          }
        }
        /// rules without arguments
        else {
          // checks if is an existing ruledeployment
          if (fieldRules[i] in VALIDATIONS) {
            if (!VALIDATIONS[fieldRules[i]](object[key]))
              errors += `El campo '${key}' ${errors_es.get(fieldRules[i])}.\n`;
          }
        }
      }
    }

    if (errors !== '')
      throw { code: 400, message: errors };
  }

  return {
    validate : validate
  }
}();